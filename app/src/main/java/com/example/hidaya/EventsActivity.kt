package com.example.hidaya

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hidaya.databinding.ActivityEventsBinding
import java.time.LocalTime
import java.util.Date
import android.widget.Button
import androidx.core.view.get


class EventsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventsBinding
    private lateinit var menuBarToggle: ActionBarDrawerToggle
    private lateinit var selectedEvents: List<Event>


    var lezing = Event("Reis door de werelden", "12/07/2023", "13:00 uur", 1.50, TypeEvent.LEZING)
    var iftar = Event("Iftar", "12/07/2023", "13:00 uur", 1.50,TypeEvent.FUN)
    var kahoot = Event("kahoot","12/07/2023", "13:00 uur", 3.0, TypeEvent.FUN)
    var lezingOverVatsen = Event("Het belang van vasten", "12/07/2023", "13:00 uur", 1.50,TypeEvent.LEZING)

    val events = listOf<Event>(lezing,iftar,kahoot,lezingOverVatsen)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedEvents = getListOfSelectedEvents()
        var adapter = EventsAdapter(getListOfSelectedEvents())
        binding.eventsRecycleView.adapter = adapter
        binding.eventsRecycleView.layoutManager = LinearLayoutManager(this)

        menuBarToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.menu_open, R.string.menu_close)
        binding.drawerLayout.addDrawerListener(menuBarToggle)
        menuBarToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
            }
            true
        }

        binding.checkbox1.setOnCheckedChangeListener { _, _ ->

            selectedEvents = getListOfSelectedEvents()
            adapter = EventsAdapter(selectedEvents)
            binding.eventsRecycleView.adapter = adapter
        }

        binding.checkbox2.setOnCheckedChangeListener { _, _ ->
            selectedEvents = getListOfSelectedEvents()
            adapter = EventsAdapter(selectedEvents)
            binding.eventsRecycleView.adapter = adapter
        }





    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // we need to do this to respond correctly to clicks on menu items, otherwise it won't be caught
        if(menuBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    fun getListOfSelectedEvents() : List<Event> {
        var list = mutableListOf<Event>()
        events.forEach(){event ->
            if(binding.checkbox1.isChecked && event.type == TypeEvent.FUN){
                list.add(event)
            }
            if(binding.checkbox2.isChecked && event.type == TypeEvent.LEZING ){
                list.add(event)
            }
        }
        return list
    }



}