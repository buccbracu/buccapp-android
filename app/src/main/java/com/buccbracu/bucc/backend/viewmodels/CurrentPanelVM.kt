package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.remote.api.DeptMemberService
import com.buccbracu.bucc.backend.remote.models.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import javax.inject.Inject


@HiltViewModel
open class CurrentPanelVM @Inject constructor(
    private val sharedR: SharedRepository,
    private val DeptMem: DeptMemberService
): ViewModel() {

    val session = sharedR.session
    val profile = sharedR.profile

    fun getMembers(
        setMembers: (List<Member>) -> Unit,
        setLoading: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            session.value?.let {
                val response = DeptMem.getDeptMembers(session.value!!.authJsToken).awaitResponse()
                response.body()?.let {
                    setMembers(response.body()!!.users)
                    setLoading(false)
                }
            }
        }
    }

    // all members for gb and hr
    fun getAllMembers(
        setMembers: (List<Member>) -> Unit,
        setLoading: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            session.value?.let {
                val response = DeptMem.getAllMembers(session.value!!.authJsToken).awaitResponse()
                response.body()?.let {
                    println(response.errorBody())
                    setMembers(response.body()!!.user)
                    setLoading(false)
                }
            }
        }
    }

}