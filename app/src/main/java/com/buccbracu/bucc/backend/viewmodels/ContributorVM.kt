package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.remote.api.GithubService
import com.buccbracu.bucc.backend.remote.api.TaskService
import com.buccbracu.bucc.backend.remote.models.Contributor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
open class ContributorVM @Inject constructor(
    private val Github: GithubService
): ViewModel() {

    fun getContributors(setList: (List<Contributor>?) -> Unit){
        viewModelScope.launch {
            val response = Github.getDataFromUrl().awaitResponse()
            val error = response.errorBody()
            if(response.isSuccessful){
                setList(response.body())
            }
        }
    }
}