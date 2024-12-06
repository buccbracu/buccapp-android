package com.buccbracu.bucc.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buccbracu.bucc.backend.local.repositories.SharedRepository
import com.buccbracu.bucc.backend.remote.api.TaskService
import com.buccbracu.bucc.backend.remote.models.NewTask
import com.buccbracu.bucc.backend.remote.models.TaskData
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
                    println("Setn descrittoin: ${task.description}")
                    println(body.taskDescription)
                    onSuccess()
                }
            }
        }
    }
}