package com.example.hidaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hidaya.databinding.ActivityAddNewEventBinding
import com.example.hidaya.databinding.ActivityDeleteEventBinding

class DeleteEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}