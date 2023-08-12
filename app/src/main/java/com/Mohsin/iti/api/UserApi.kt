package com.Mohsin.iti.api

import com.Mohsin.iti.model.Comment
import com.Mohsin.iti.model.Post
import com.Mohsin.iti.model.ResponseUsersList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("/api/users")
    suspend fun getUser():Response<ResponseUsersList>

    @GET("posts")
    suspend fun getPosts():Response<ArrayList<Post>>

    @GET("posts")
    suspend fun getPostsByUser(@Query("userId") userId:Int):Response<ArrayList<Post>>
@GET("posts/{post_id}/comments")
    suspend fun getComments(@Path("post_id") postId:Int):Response<ArrayList<Comment>>
}