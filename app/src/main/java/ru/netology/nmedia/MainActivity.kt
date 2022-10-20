package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
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
        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(
            likeClickListener = {
                viewModel.likeById(it.id)
            },
            shareClickListener = {
                viewModel.share(it.id)
            }
        )
        binding.posts.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}