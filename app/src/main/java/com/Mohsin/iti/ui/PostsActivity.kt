package com.Mohsin.iti.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.Mohsin.iti.OnClickListener
import com.Mohsin.iti.R
import com.Mohsin.iti.adapter.PostsAdapter
import com.Mohsin.iti.api.RetrofitClient
import com.Mohsin.iti.api.UserApi
import com.Mohsin.iti.databinding.ActivityPostsBinding
import com.Mohsin.iti.model.Post



class PostsActivity : AppCompatActivity(), OnClickListener {
    private lateinit var adapter: PostsAdapter
    private lateinit var sharedPref :SharedPreferences
    lateinit var retrofit:UserApi
    private lateinit var binding: ActivityPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref=applicationContext.getSharedPreferences("UserPref", MODE_PRIVATE)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
         retrofit=RetrofitClient.getInstance()

      binding.btnGetPosts.setOnClickListener {
          lifecycleScope.launchWhenStarted { val response=retrofit.getPostsByUser(binding.etId.text.toString().toInt())
              if(response.isSuccessful){
                  adapter = PostsAdapter(response.body() ?: listOf(),this@PostsActivity)
                  binding.rvPosts.adapter = adapter
                  binding.rvPosts.layoutManager = LinearLayoutManager(this@PostsActivity)
              }
              else{
                  Toast.makeText(this@PostsActivity,"error",Toast.LENGTH_LONG).show()
              }
          } }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_posts_activity,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout -> {
              val editor = sharedPref.edit()
                editor.remove("USERNAME")
                editor.remove("PASSWORD")
                editor.putBoolean("IS_LOGIN",false)
                editor.commit()
                startActivity(Intent(this, SplashScreenActivity::class.java))
                finish()
                true
            }
            else -> {
              super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onClick(post: Post, position: Int) {
        lifecycleScope.launchWhenStarted { val response=retrofit.getComments(post.id)
            if(response.isSuccessful){

                val comment=response.body()?.get(0)
                val intent=Intent(this@PostsActivity,CommentsActivity::class.java)
                intent.putExtra("post_id",comment?.postId)
                intent.putExtra("comment_id",comment?.id)
                intent.putExtra("email",comment?.email)
                intent.putExtra("body",comment?.body)
                intent.putExtra("name",comment?.name)
                startActivity(intent)
            }
            else{
                Toast.makeText(this@PostsActivity,"error",Toast.LENGTH_LONG).show()
            }
        }
    }


}