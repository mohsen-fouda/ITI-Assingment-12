package com.Mohsin.iti.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Mohsin.iti.OnClickListener
import com.Mohsin.iti.databinding.CustomPostsBinding
import com.Mohsin.iti.model.Post


class PostsAdapter(
    private var postsList: List<Post>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {


    inner class PostsViewHolder(val binding: CustomPostsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(CustomPostsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val posts = postsList[position]
        holder.binding.apply {
            uName.text = "id: ${posts.id}"
            theDate.text = "postId: ${posts.userId}"
            PostContent.text = posts.body
            Readbtn.setOnClickListener {
                listener.onClick(posts, position)
            }
        }


    }

    override fun getItemCount(): Int {
        return postsList.size
    }
}