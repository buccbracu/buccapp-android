package com.buccbracu.bucc.ui.screens.Blog

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.remote.models.Blog
import com.buccbracu.bucc.backend.viewmodels.BlogVM
import com.buccbracu.bucc.components.blogs.BlogView
import kotlinx.coroutines.launch

@Composable
fun ViewBlogs(){
    val blogvm: BlogVM = hiltViewModel()
    val scope = rememberCoroutineScope()
    var blogList by remember{
        mutableStateOf<List<Blog>>(emptyList())
    }
    LaunchedEffect(Unit) {
        scope.launch {
            blogvm.getAllBlogs { list ->
                blogList = list
            }
        }
    }

    LazyColumn {
        itemsIndexed(blogList){ index, blog ->
            BlogView(blog)
        }
    }
}