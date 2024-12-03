package com.buccbracu.bucc.backend.remote.models

data class Blog(
    val _id: String,
    val title: String,
    val description: String,
    val featuredImage: String,
    val content: List<Content>,
    val category: String?,
    val tags: List<String> = listOf(),
    val author: Author,
    val createdDate: String,
    val status: String = "draft",
    val lastUpdate: String
)

data class Author(
    val authorId: String?,
    val authorName: String?,
    val authorEmail: String?,
    val authorDesignation: String?,
    val authorDepartment: String?
)

data class Content(
    val type: String,
    val content: List<ContentItem>? = null, // For text or nested content
    val attrs: ContentAttributes? = null // For attributes like image source, heading level, etc.
)

data class ContentItem(
    val type: String,
    val text: String? = null,
    val marks: List<ContentMark>? = null,
    val href: String? = null // For links
)

data class ContentAttributes(
    val level: Int? = null,
    val src: String? = null,
    val alt: String? = null,
    val title: String? = null
)

data class ContentMark(
    val type: String
)
