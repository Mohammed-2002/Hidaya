package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hidaya.databinding.ActivityEventUnregisterBinding
import com.example.hidaya.databinding.ActivityLoginBinding

class EventUnregisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventUnregisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventUnregisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = UserManger.currentUser
        val event: Event = intent.getSerializableExtra("event") as Event
        binding.btnNee.setOnClickListener(){
            val intent = Intent(this, EventsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.startActivity(intent)
        }
        binding.btnJa.setOnClickListener(){
            if (currentUser != null) {
                EventFileRepository.getEvent(event.eventSubject) {eventToUpdate->
                    eventToUpdate?.usersMails?.remove(currentUser.email)
                    if (eventToUpdate != null) {
                        EventFileRepository.saveEvent(eventToUpdate)
                    }
                    val intent = Intent(this, EventsActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    this.startActivity(intent)
                }
            }
        }

    }
}