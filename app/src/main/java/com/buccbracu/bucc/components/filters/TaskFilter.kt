package com.buccbracu.bucc.components.filters

import com.buccbracu.bucc.backend.remote.models.TaskData

fun filterTask(query: String, list: List<TaskData>): List<TaskData> {
    val lowerCaseQuery = query.lowercase()

    return list.filter { task ->
        task.taskTitle.lowercase().contains(lowerCaseQuery) ||
        (task.taskDescription?.lowercase()?.contains(lowerCaseQuery) ?: false) ||
        (task.fromDept?.lowercase()?.contains(lowerCaseQuery) ?: false) ||
        (task.fromDesignation?.lowercase()?.contains(lowerCaseQuery) ?: false) ||
        (task.toDept?.lowercase()?.contains(lowerCaseQuery) ?: false) ||
        (task.toDesignation?.lowercase()?.contains(lowerCaseQuery) ?: false) ||
        (task.deadline?.lowercase()?.contains(lowerCaseQuery) ?: false) ||
        task.acceptedBy.any { it.lowercase().contains(lowerCaseQuery) } ||
        task.comment.lowercase().contains(lowerCaseQuery) ||
        task.status.lowercase().contains(lowerCaseQuery)
    }
}
