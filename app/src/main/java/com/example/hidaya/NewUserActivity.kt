package com.example.hidaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hidaya.databinding.ActivityEventsBinding
import com.example.hidaya.databinding.ActivityNewUserBinding

class NewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}