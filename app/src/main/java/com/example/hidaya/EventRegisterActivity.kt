package com.example.hidaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hidaya.databinding.ActivityEventRegisterBinding


class EventRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val event: Event = intent.getSerializableExtra("event") as Event
        binding.textView.text = "Ben je zeker dat je wil inschrijven voor event ${event.eventSubject}"
    }
}