package com.Mohsin.iti

import com.Mohsin.iti.model.Post

interface OnClickListener {
    fun onClick(post: Post, position:Int)
}