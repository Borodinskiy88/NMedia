package ru.netology.nmedia

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val liked:Int = 999,
    val share:Int = 102,
    val remove:Int = 10200,
    val likedByMe: Boolean = false

)