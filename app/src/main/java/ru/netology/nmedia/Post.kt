package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val liked: Int = 0,
    val share: Int = 0,
    val views: Int = 0,
    val likedByMe: Boolean = false

)