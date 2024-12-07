package com.buccbracu.bucc.backend.remote.api

import com.buccbracu.bucc.backend.remote.TASK
import com.buccbracu.bucc.backend.remote.models.NewTask
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.backend.remote.models.UpdateTask
import retrofit2.Call
import retrofit2.http.*

interface TaskService {
    // Fetch all $TASK
    @GET(TASK)
    fun getTasks(@Header("Cookie") cookie: String): Call<List<TaskData>>

    // Fetch a single task by ID
    @GET("$TASK/{id}")
    fun getTaskById(
        @Header("Cookie") cookie: String,
        @Path("id") id: String
    ): Call<TaskData>

    // Create a new task
    @POST(TASK)
    fun createTask(
        @Header("Cookie") cookie: String,
        @Body task: NewTask
    ): Call<TaskData>

    // Update a task by ID
    @PATCH("$TASK/{id}")
    fun updateTask(
        @Header("Cookie") cookie: String,
        @Path("id") id: String,
        @Body task: UpdateTask
    ): Call<TaskData>

    // Delete a task by ID
    @DELETE("$TASK/{id}")
    fun deleteTask(
        @Header("Cookie") cookie: String,
        @Path("id") id: String
    ): Call<Void>
}
