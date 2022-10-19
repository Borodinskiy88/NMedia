package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.root.setOnClickListener{ Toast.makeText(this, "Биндинг", Toast.LENGTH_LONG).show()}
//        binding.liked.setOnClickListener { Toast.makeText(this, "Лайки", Toast.LENGTH_LONG).show() }
//        binding.avatar.setOnClickListener{ Toast.makeText(this, "Аватар", Toast.LENGTH_SHORT).show()}


        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                liked.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                )
                likedCount.text = reformatCount(post.liked)
                shareCount.text = reformatCount(post.share)
                removeCount.text = reformatCount(post.remove)
            }

        }
        binding.liked.setOnClickListener {
            viewModel.like()
        }

        binding.share.setOnClickListener {
            viewModel.share()
        }

    }

}