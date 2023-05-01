package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hidaya.databinding.ActivityAddNewEventBinding
import com.example.hidaya.databinding.ActivityEventRegisterBinding
import com.example.hidaya.databinding.ActivityEventsBinding

class AddNewEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddEvent.setOnClickListener(){
            val eventSubject = binding.etEventSubject.text.toString()
            val date = binding.etDate.text.toString()
            val time= binding.etTime.text.toString()
            val durationInHour = binding.etEventDuration.text.toString()
            var typeEvent: TypeEvent = if(binding.isEenLezing.isChecked){
                TypeEvent.LEZING
            } else{
                TypeEvent.FUN
            }
            val eventFileRepository = EventFileRepository(this)
            val events = eventFileRepository.load()
            val event = Event(eventSubject,date,time, durationInHour ,typeEvent, mutableListOf())
            events.toMutableList().apply {
                add(event)
                eventFileRepository.save(this)
            }
            val intent = Intent(this, EventsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.startActivity(intent)
        }
    }
}