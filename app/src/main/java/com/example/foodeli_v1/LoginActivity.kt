package com.example.foodeli_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.foodeli_v1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.loginButton.setOnClickListener {
            val intent=Intent(this, SignActivity::class.java)
            startActivity(intent)
        }

        binding.loginRegister.setOnClickListener {
            val intent=Intent(this, SignActivity::class.java)
            startActivity(intent)
        }
    }



}