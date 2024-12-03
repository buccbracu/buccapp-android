package com.buccbracu.bucc.ui.screens.Blog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.remote.models.Blog
import com.buccbracu.bucc.backend.viewmodels.BlogVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.components.blogs.BlogView
import kotlinx.coroutines.launch

@Composable
fun ViewBlogs(navController: NavHostController){
    val blogvm: BlogVM = hiltViewModel()
    val scope = rememberCoroutineScope()
    var blogList by remember{
        mutableStateOf<List<Blog>>(emptyList())
    }
    var isLoading by remember{
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        scope.launch {
            blogvm.getAllBlogs { list ->
                blogList = list
                isLoading = false
            }
        }
    }

    if(isLoading){
        NoButtonCircularLoadingDialog(
            title = "Loading Published Blogs",
            message = "Please wait..."
        )
    }
    else{
        LazyColumn(
            modifier = Modifier
                .padding(top = 70.dp)
        ) {
            items(blogList){blog ->
                BlogListView(
                    title = blog.title,
                    description = blog.description,
                    category = blog.category ?: "N/A",
                    author = blog.author.authorName?: "N/A"
                ) {
                    navController.navigate("BlogDetail/${blog._id}")
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
    onClick: () -> Unit
){
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = title
            )
            Text(
                text = description
            )
            Text(
                    text = category
                )

            Text(
                text = author
            )
        }
    }
}