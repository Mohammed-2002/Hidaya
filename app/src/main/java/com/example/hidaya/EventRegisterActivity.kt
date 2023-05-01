package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hidaya.databinding.ActivityEventRegisterBinding


class EventRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = UserManger.currentUser
        val event: Event = intent.getSerializableExtra("event") as Event
        binding.textView.text = "Ben je zeker dat je wil inschrijven voor event ${event.eventSubject}"
        binding.neeButton.setOnClickListener(){
            val intent = Intent(this, EventsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.startActivity(intent)
        }
        binding.jaButton.setOnClickListener(){
            if (currentUser != null) {
                val  eventFileRepository = EventFileRepository(this)
                val eventsList = eventFileRepository.load()
                var eventToUpdate = eventsList.find { it == event }
                eventToUpdate?.users?.add(currentUser)
                eventFileRepository.save(eventsList)
                val intent = Intent(this, EventsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                this.startActivity(intent)
            }

        }
    }
}