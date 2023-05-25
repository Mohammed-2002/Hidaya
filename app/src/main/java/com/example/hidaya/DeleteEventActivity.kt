package com.example.hidaya

import android.content.Intent
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

        binding.btnNo.setOnClickListener(){
            val intent = Intent(this, EventsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.startActivity(intent)
        }
        binding.btnYes.setOnClickListener(){
            val toRemoveEvent : Event = intent.getSerializableExtra("event") as Event
            EventFileRepository.removeEvent(toRemoveEvent)
            val intent = Intent(this, EventsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.startActivity(intent)
        }
    }
}