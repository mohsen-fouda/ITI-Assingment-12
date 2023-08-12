package com.Mohsin.iti.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.Mohsin.iti.adapter.CommentsAdapter
import com.Mohsin.iti.api.RetrofitClient
import com.Mohsin.iti.databinding.ActivityCommentsBinding
import com.Mohsin.iti.model.Comment

class CommentsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentsBinding
    lateinit var adapter:CommentsAdapter
    private lateinit var commentsList: ArrayList<Comment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commentsList= ArrayList()
        adapter = CommentsAdapter(commentsList)
        binding.rvComments.adapter = adapter
        binding.rvComments.layoutManager = LinearLayoutManager(this@CommentsActivity)
        val retrofit= RetrofitClient.getInstance()
        val postId = intent.getIntExtra("post_id", 0)
        lifecycleScope.launchWhenStarted { val response=retrofit.getComments(postId)
            if(response.isSuccessful){
val comments=response.body()?: listOf()
                commentsList.addAll(comments)
                adapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this@CommentsActivity,"error",Toast.LENGTH_LONG).show()
            }
        }

    }
}