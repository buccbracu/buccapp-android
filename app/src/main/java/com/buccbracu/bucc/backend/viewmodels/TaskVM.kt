package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.remote.api.TaskService
import com.buccbracu.bucc.backend.remote.models.NewTask
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.backend.remote.models.TaskOverview
import com.buccbracu.bucc.backend.remote.models.UpdateTask
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
    val profile = sharedR.profile

    fun getAllTasks(
        setTasks: (List<TaskData>) -> Unit,
        onSuccess: () -> Unit
    ){
        viewModelScope.launch {
            session.value?.let {
                val response = Task.getTasks(session.value!!.authJsToken).awaitResponse()
                val body = response.body()
                body?.let {
                    setTasks(body)
                    onSuccess()
                }
                println(response.message())
            }
        }
    }
    fun getTaskOverview(
        setTasks: (TaskOverview) -> Unit,
        onSuccess: () -> Unit
    ){
        viewModelScope.launch {
            session.value?.let {
                val response = Task.getTaskOverview(session.value!!.authJsToken).awaitResponse()
                val body = response.body()
                body?.let {
                    setTasks(body!!.taskCounts)
                    onSuccess()
                }
            }
        }
    }

    fun createTask(task: NewTask, onSuccess: () -> Unit){
        viewModelScope.launch {
            session.value?.let {
                val response = Task.createTask(session.value!!.authJsToken, task).awaitResponse()
                val body = response.body()
                body?.let {
                    onSuccess()
                }
            }
        }
    }

    fun updateTask(task: UpdateTask, onSuccess: () -> Unit){
        viewModelScope.launch {
            session.value?.let {
                val response = Task.updateTask(
                    cookie = session.value!!.authJsToken,
                    task = task,
                    id = task._id
                ).awaitResponse()
                val body = response.body()
                body?.let {
                    onSuccess()
                }
            }
        }
    }

    fun deleteTask(id: String, onSuccess: () -> Unit){
        viewModelScope.launch {
            session.value?.let {
                val response = Task.deleteTask(session.value!!.authJsToken, id).awaitResponse()
                if(response.code() == 200){
                    println("deleted")
                    onSuccess()
                }
            }
        }
    }

}