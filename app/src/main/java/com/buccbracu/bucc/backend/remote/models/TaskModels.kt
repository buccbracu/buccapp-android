package com.buccbracu.bucc.backend.remote.models

data class TaskData(
    val _id: String?,
    val taskTitle: String,
    val taskDescription: String?,
    val fromDept: String?,
    val fromDesignation: String?,
    val toDept: String?,
    val toDesignation: String?,
    val assignDate: String? = null,
    val deadline: String?,
    val acceptedBy: List<String> = emptyList(),
    val dateCompleted: String? = null,
    val comment: String = "",
    val status: String = "pending"
)

data class NewTask(
    val taskTitle: String,
    val taskDescription: String,
    val toDept: String,
    val toDesignation: String,
    val deadline: String
)

data class UpdateTask(
    val deadline: String,
    val acceptedBy: List<String> = emptyList(),
    val dateCompleted: String? = null,
    val comment: String = "",
    val status: String = "pending"
)