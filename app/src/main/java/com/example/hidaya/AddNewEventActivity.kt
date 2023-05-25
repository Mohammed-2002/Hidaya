package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hidaya.databinding.ActivityAddNewEventBinding

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
            EventFileRepository.getEvent(eventSubject) { RetrievedEvent ->
                if (eventSubject.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty() && durationInHour.isNotEmpty()) {
                    if(RetrievedEvent != null){
                        Toast.makeText(this, "De naam van het evenement is al gebruikt", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val event =
                            Event(
                                eventSubject,
                                date,
                                time,
                                durationInHour,
                                typeEvent,
                                mutableListOf()
                            )
                        EventFileRepository.saveEvent(event)
                        val intent = Intent(this, EventsActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        this.startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "Vul alle velden in", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}