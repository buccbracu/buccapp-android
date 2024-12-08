package com.buccbracu.bucc.components.filters

import com.buccbracu.bucc.backend.remote.models.Blog

fun blogFilter(query: String, list: List<Blog>): List<Blog> {
    return list.filter { blog ->
        blog.title.contains(query, ignoreCase = true) ||
        blog.description.contains(query, ignoreCase = true) ||
        blog.category?.contains(query, ignoreCase = true) == true ||
        blog.tags.any { tag -> tag.contains(query, ignoreCase = true) } ||
        blog.author.authorName?.contains(query, ignoreCase = true) == true ||
        blog.author.authorDesignation?.contains(query, ignoreCase = true) == true ||
        blog.author.authorDepartment?.contains(query, ignoreCase = true) == true
    }
}