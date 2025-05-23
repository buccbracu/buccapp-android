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
    val content: List<ContentItem>? = null,
    val attrs: ContentAttributes? = null,
    val text: String? = null
)

data class ContentItem(
    val type: String,
    val text: String? = null,
    val marks: List<ContentMark>? = null,
    val href: String? = null,
    val content: List<Content>? = null
)

data class ContentAttributes(
    val level: Int? = null,
    val src: String? = null,
    val alt: String? = null,
    val title: String? = null,
    val width: Float? = null,
    val height: Float? = null,
    val start: Int? = null
)

data class ContentMark(
    val type: String,
    val attrs: ContentMarkAttributes? = null
)

data class ContentMarkAttributes(
    val href: String? = null,
    val target: String? = null,
    val rel: String? = null,
    val `class`: String? = null
)

