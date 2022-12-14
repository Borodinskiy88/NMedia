package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val liked: Int = 0,
    val share: Int = 0,
    val views: Int = 0,
    val likedByMe: Boolean = false,
    val videoUrl: String? = null
) {

    fun toDto() = Post(id, author, content, published, liked, share, views, likedByMe, videoUrl)

    companion object {
        fun fromDto(dto: Post) = PostEntity(
            dto.id, dto.author, dto.content, dto.published,
            dto.liked, dto.share, dto.views, dto.likedByMe, dto.videoUrl
        )
    }
}