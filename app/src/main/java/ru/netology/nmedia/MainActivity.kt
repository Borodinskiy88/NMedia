package ru.netology.nmedia

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.root.setOnClickListener{ Toast.makeText(this, "Биндинг", Toast.LENGTH_LONG).show()}
//        binding.liked.setOnClickListener { Toast.makeText(this, "Лайки", Toast.LENGTH_LONG).show() }
//        binding.avatar.setOnClickListener{ Toast.makeText(this, "Аватар", Toast.LENGTH_SHORT).show()}

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "6 октября в 16:36",
            likedByMe = false
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                liked?.setImageResource(R.drawable.ic_liked_favorite_24)
            }
            likedCount?.text = reformatCount(post.likes)


            liked?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                liked.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likedCount?.text = reformatCount(post.likes)
            }

            shareCount?.text = reformatCount(post.share)

            share?.setOnClickListener {
                post.share++
                shareCount?.text = reformatCount(post.share)
            }

            removeCount?.text = reformatCount(post.remove)
        }
    }
}