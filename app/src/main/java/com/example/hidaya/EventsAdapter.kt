package com.example.hidaya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventsAdapter(val events: List<Event>): RecyclerView.Adapter<EventsAdapter.EventViewHolder>(){

    inner class EventViewHolder(currentItemView: View) : RecyclerView.ViewHolder(currentItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsAdapter.EventViewHolder, position: Int) {
        val currentEvent = events[position]
        holder.itemView.apply{
            findViewById<TextView>(R.id.event_subject).text = currentEvent.eventSubject
            findViewById<TextView>(R.id.event_date).text = currentEvent.date.toString()
            findViewById<TextView>(R.id.event_time).text = currentEvent.time.toString()
            findViewById<TextView>(R.id.event_duration).text = currentEvent.durationInHour.toString()
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}