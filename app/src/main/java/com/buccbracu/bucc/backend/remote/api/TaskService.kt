package com.buccbracu.bucc.backend.remote.api

import com.buccbracu.bucc.backend.remote.models.NewTask
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.backend.remote.models.UpdateTask
import retrofit2.Call
import retrofit2.http.*

interface TaskService {
    // Fetch all tasks
    @GET("tasks")
    fun getTasks(@Header("Cookie") cookie: String): Call<List<TaskData>>

    // Fetch a single task by ID
    @GET("tasks/{id}")
    fun getTaskById(
        @Header("Cookie") cookie: String,
        @Path("id") id: String
    ): Call<TaskData>

    // Create a new task
    @POST("tasks")
    fun createTask(
        @Header("Cookie") cookie: String,
        @Body task: NewTask
    ): Call<TaskData>

    // Update a task by ID
    @PATCH("tasks/{id}")
    fun updateTask(
        @Header("Cookie") cookie: String,
        @Path("id") id: String,
        @Body task: UpdateTask
    ): Call<TaskData>

    // Delete a task by ID
    @DELETE("tasks/{id}")
    fun deleteTask(
        @Header("Cookie") cookie: String,
        @Path("id") id: String
    ): Call<Void>
}
