package com.Mohsin.iti.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.Mohsin.iti.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("Name")
        val gender = intent.getStringExtra("Gender")
        val sports = intent.getStringExtra("Sports")
        Toast.makeText(
            baseContext,
            "Hello $name you're $gender and your sports are $sports",
            Toast.LENGTH_LONG
        ).show()

    }
}