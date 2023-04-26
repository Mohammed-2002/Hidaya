package com.example.hidaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hidaya.databinding.ActivityInschrijvingBinding
import com.example.hidaya.databinding.ActivityLoginBinding



class InschrijvingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInschrijvingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInschrijvingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val event: Event = intent.getSerializableExtra("event") as Event
        binding.textView.text = "Ben je zeker dat je wil inschrijven voor event ${event.eventSubject}"
    }
}