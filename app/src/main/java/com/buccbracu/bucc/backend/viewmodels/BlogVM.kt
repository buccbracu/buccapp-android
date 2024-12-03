package com.buccbracu.bucc.backend.viewmodels

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.remote.api.BlogService
import com.buccbracu.bucc.backend.remote.api.DeptMemberService
import com.buccbracu.bucc.backend.remote.models.Blog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
open class BlogVM @Inject constructor(
    private val sharedR: SharedRepository,
    private val Blog: BlogService
): ViewModel() {

    val session = sharedR.session

    fun getAllBlogs(setBlogs: (List<Blog>) -> Unit){
        viewModelScope.launch {
            session.value?.let {
                val response = Blog.getBlogs(session.value!!.authJsToken).awaitResponse()
                val body = response.body()
                if(body != null){
                    setBlogs(body.filter { it.status.lowercase(Locale.ROOT) == "published" })
                }
                else{
                    setBlogs(emptyList())
                }
                body?.let {
                    body.forEach { blog ->
                        blog.content.forEach { content ->
                            println(content.attrs?.level)
                        }
                    }
                }
            }
        }
    }
    fun getBlog(
        id: String,
        setBlog: (Blog) -> Unit
    ){
        viewModelScope.launch {
            session.value?.let {
                val response = Blog.getBlog(session.value!!.authJsToken, id).awaitResponse()
                val body = response.body()
                body?.let {
                    setBlog(it)
                }
            }
        }
    }
}