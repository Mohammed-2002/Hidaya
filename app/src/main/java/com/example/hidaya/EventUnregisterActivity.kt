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
            this.startActivity(intent)
        }
        binding.btnJa.setOnClickListener(){
            if (currentUser != null) {
                val  eventFileRepository = EventFileRepository(this)
                val eventsList = eventFileRepository.load()
                var eventToUpdate = eventsList.find { it == event }
                eventToUpdate?.users?.remove(currentUser)
                eventFileRepository.save(eventsList)
                val intent = Intent(this, EventsActivity::class.java)
                this.startActivity(intent)
            }

        }

    }
}