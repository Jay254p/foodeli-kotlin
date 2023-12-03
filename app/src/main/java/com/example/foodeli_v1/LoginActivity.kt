package com.example.foodeli_v1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodeli_v1.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.loginButton.setOnClickListener {
            val intent=Intent(this, Location::class.java)
            startActivity(intent)
        }

        binding.loginRegister.setOnClickListener {
            val intent=Intent(this, SignActivity::class.java)
            startActivity(intent)
        }
    }



}