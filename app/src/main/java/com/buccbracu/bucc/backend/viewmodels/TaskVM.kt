package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.remote.api.TaskService
import com.buccbracu.bucc.backend.remote.models.NewTask
import com.buccbracu.bucc.backend.remote.models.TaskData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

import javax.inject.Inject

@HiltViewModel
open class TaskVM @Inject constructor(
    private val sharedR: SharedRepository,
    private val Task: TaskService
): ViewModel() {

    val session = sharedR.session

    fun getAllTasks(setTasks: (List<TaskData>) -> Unit){
        viewModelScope.launch {
            session.value?.let {
                val response = Task.getTasks(session.value!!.authJsToken).awaitResponse()
                val body = response.body()
                body?.let {
                    setTasks(body)
                }
            }
        }
    }

    fun createTask(task: NewTask){
        println("Started")
        viewModelScope.launch {
            session.value?.let {
                println(session.value!!.authJsToken)
                val response = Task.createTask(session.value!!.authJsToken, task).awaitResponse()
                val body = response.body()
                println("RESPOSNE MESSAGE ${response.message()} ${response.code()} ${response.errorBody()}")
                body?.let {
                    println("TASK CREATION ${body.taskTitle}")

                }
            }
        }
    }
}