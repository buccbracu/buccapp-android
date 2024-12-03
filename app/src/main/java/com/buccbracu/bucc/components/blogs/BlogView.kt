package com.buccbracu.bucc.components.blogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.remote.models.Blog
import com.buccbracu.bucc.backend.viewmodels.BlogVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import kotlinx.coroutines.launch

/*
1. Image view
2. Proper Formatting of heading and subheading
3. Anchor texts fix
 */

@Composable
fun BlogView(
    id: String?,
    back: () -> Unit
) {
    val blogvm: BlogVM = hiltViewModel()
    val scope = rememberCoroutineScope()
    var blog by remember{
        mutableStateOf<Blog?>(null)
    }
    var isLoading by remember{
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        scope.launch {
            id?.let {
                blogvm.getBlog(it){
                    blog = it
                    isLoading = false
                }
            }
        }
    }
    if(isLoading){
        NoButtonCircularLoadingDialog(
            title = "Loading Blog",
            message = "Please wait..."
        )
    }
    else{
        blog?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(top = 70.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = back
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Back"
                    )
                }
                // Title
                Text(
                    text = blog!!.title,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Description
                Text(
                    text = blog!!.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )

                // Author
                Text(
                    text = "By ${blog!!.author.authorName}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Content
                RenderContent(blog!!.content)
            }
        }
    }
}

