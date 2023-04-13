package com.example.hidaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Events
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hidaya.databinding.ActivityEventsBinding
import java.time.LocalTime
import java.util.Date

class EventsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventsBinding

    var lezing = Event("Reis door de werelden", Date(122, 11, 31), LocalTime.of(13, 0,0), 1.50)
    var iftar = Event("Iftar", Date(122, 4, 25), LocalTime.of(19, 30,0), 1.50)
    var kahoot= Event("kahoot", Date(122, 5, 20), LocalTime.of(18, 30,0), 3.0)
    var lezingOverVatsen = Event("Het belang van vasten", Date(122, 4, 7), LocalTime.of(17, 45,0), 1.50)

    val events = listOf<Event>(lezing,iftar,kahoot,lezingOverVatsen)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter = EventsAdapter(events)
        binding.eventsRecycleView.adapter = adapter
        binding.eventsRecycleView.layoutManager = LinearLayoutManager(this)

    }
}