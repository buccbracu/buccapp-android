package com.buccbracu.bucc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ScreenSearchDesktop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.buccbracu.bucc.backend.remote.models.Blog
import com.buccbracu.bucc.backend.viewmodels.BlogVM
import com.buccbracu.bucc.components.BasicDialog
import com.buccbracu.bucc.components.EmptyScreenText
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.components.SearchBar
import com.buccbracu.bucc.components.appendBulletPoint
import com.buccbracu.bucc.components.checkServer
import com.buccbracu.bucc.components.filters.blogFilter
import kotlinx.coroutines.launch

@Composable
fun ViewBlogs(navController: NavHostController){
    val blogvm: BlogVM = hiltViewModel()
    val scope = rememberCoroutineScope()
    var blogList by remember{
        mutableStateOf<List<Blog>>(emptyList())
    }
    var filteredList by remember{
        mutableStateOf<List<Blog>>(emptyList())
    }
    var isLoading by remember{
        mutableStateOf(true)
    }
    var showHelp by remember{
        mutableStateOf(false)
    }
    var hasInternet by remember{
        mutableStateOf(false)
    }
    val (query, setQuery) = remember{ mutableStateOf("") }
    LaunchedEffect(Unit) {
        scope.launch {
            hasInternet= checkServer()
            if(hasInternet){
                blogvm.getAllBlogs { list ->
                    blogList = list
                    filteredList = list
                    isLoading = false
                }
            }
            else{
                isLoading = false
            }

        }
    }
    LaunchedEffect(query) {
        if(query != ""){
            scope.launch {
                filteredList = blogFilter(query, blogList)
            }
        }
    }

    if(isLoading){
        NoButtonCircularLoadingDialog(
            title = "Loading Published Blogs",
            message = "Please wait..."
        )
    }
    else if(!hasInternet){
        EmptyScreenText("Connect to the Internet and Try Again.")
    }
    else{


        Column(
            modifier = Modifier
                .padding(top = 70.dp, bottom = 50.dp)
        ) {
            SearchBar(
                query = query,
                onChange = setQuery,
                label = "Search Blog",
                placeholder = "Title || Description || Author || Tag || Category",
                onClear = {
                    setQuery("")
                    filteredList = blogList
                },
                leadingIcon = Icons.Outlined.ScreenSearchDesktop,
                isLeadingIconButton = true,
                leadingIconAction = {
                    showHelp = true
                }
            )
            if(filteredList.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("No Blogs containing $query is found.")
                }
            }
            else{
                LazyColumn(

                ) {
                    items(filteredList) { blog ->
                        BlogListView(
                            title = blog.title,
                            description = blog.description,
                            category = blog.category ?: "N/A",
                            author = blog.author.authorName ?: "N/A",
                            image = blog.featuredImage
                        ) {
                            navController.navigate("BlogView/${blog._id}")
                        }

                    }

                }
            }
        }

        if(showHelp){
            BasicDialog(
                onDismiss = {
                    showHelp = false
                }
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Blog Search Guide",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "You can search for blogs based on the following information:",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Searchable fields list
                        Text(
                            text = buildAnnotatedString {
                                appendBulletPoint("Title")
                                appendBulletPoint("Description")
                                appendBulletPoint("Category", ": Search by blog category names.")
                                appendBulletPoint("Tags", ": Any tag associated with the blog.")
                                appendBulletPoint("Author name", ": The name of the blog author.")
                                appendBulletPoint("Author designation", ": Must use full designations (e.g., \"Senior Executive\", not \"SE\").")
                                appendBulletPoint("Author department", ": Must use full department names (e.g., \"Event Management\", not \"EM\").")
                            },
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Note
                        Text(
                            text = "Note: For accurate results, ensure you use complete and exact terms. Abbreviations or partial matches, especially for designations and departments, may not yield results.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }
                }

            }
        }

    }
}

@Composable
fun BlogListView(
    title: String,
    description: String,
    category: String = "",
    author: String = "",
    image: String,
    onClick: () -> Unit
){
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Box(){
            AsyncImage(
                model = image,
                contentDescription = "Blog Image",
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = 1f
                    },
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent.copy(alpha = 0.3f),
                                Color.Black.copy(alpha = 1f),

                                ),

                            )
                    )
                    .padding(10.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.W900, // Boldest weight
                    fontSize = 20.sp
                )
                Text(
                    text = description,
                    color = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.W600, // Medium bold
                    fontSize = 14.sp,
                    maxLines = 2, // Limit to 2 lines
                    overflow = TextOverflow.Ellipsis // Show "..." if text overflows
                )

                Text(
                    text = "By $author",
                    color = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.W300, // Lighter weight
                    fontSize = 12.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                contentAlignment = Alignment.TopEnd

            ){
                ElevatedCard(
                    colors = CardDefaults.cardColors(Color(123, 31, 162)),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ){
                    Text(
                        text = category,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

        }
    }
}