package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hidaya.databinding.ActivityEventRegisterBinding


class EventRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = UserManger.currentUser
        val event: Event = intent.getSerializableExtra("event") as Event
        binding.textView.text = "Ben je zeker dat je wil inschrijven voor het volgende evenement: ${event.eventSubject}"
        binding.neeButton.setOnClickListener(){
            val intent = Intent(this, EventsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.startActivity(intent)
        }
        binding.jaButton.setOnClickListener(){
            if (currentUser != null) {
                EventFileRepository.getEvent(event.eventSubject){retrievedEvent ->
                    retrievedEvent?.usersMails?.add(currentUser.email)
                    if (retrievedEvent != null) {
                        EventFileRepository.saveEvent(retrievedEvent)
                        val intent = Intent(this, EventsActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        this.startActivity(intent)
                    }
                }
            }
        }
    }
}