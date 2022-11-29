package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.*
import ru.netology.nmedia.databinding.FragmentSinglePostBinding
import ru.netology.nmedia.utils.StringArg

class SinglePostFragment : Fragment() {
    companion object {
        var Bundle.postIdArg by StringArg
    }

    private val viewModel by viewModels<PostViewModel>(ownerProducer = ::requireParentFragment)
    private lateinit var binding: FragmentSinglePostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSinglePostBinding.inflate(
            inflater,
            container,
            false
        )

        val postId = arguments?.postIdArg?.toLong()
        var curPost: Post?

        val viewHolder = PostViewHolder(
            binding.post,
            object : OnInteractionListener {

                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                    val text = post.content
                    val bundle = Bundle()
                    bundle.putString("editedText", text)
                    findNavController().navigate(
                        R.id.action_singlePostFragment_to_editPostFragment,
                        bundle
                    )

                }

                override fun onVideo(post: Post) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.videoUrl))
                    startActivity(intent)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                    findNavController().navigateUp()
                }

                override fun onShare(post: Post) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val shareIntent =
                        Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(shareIntent)
                }

                override fun onOpenPost(post: Post) {}

            }
        )

        viewModel.data.observe(viewLifecycleOwner) { posts ->
            curPost = posts.find { it.id == postId }
            curPost?.let { viewHolder.bind(it) }
        }

        return binding.root
    }

}