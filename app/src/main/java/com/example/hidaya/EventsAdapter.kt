package com.example.hidaya

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventsAdapter(val events: List<Event>): RecyclerView.Adapter<EventsAdapter.EventViewHolder>(){

    inner class EventViewHolder(currentItemView: View) : RecyclerView.ViewHolder(currentItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsAdapter.EventViewHolder, position: Int) {
        val currentUser = UserManger.currentUser
        val currentEvent : Event = events[position]
        holder.itemView.apply{
            findViewById<TextView>(R.id.event_subject).text = currentEvent.eventSubject
            findViewById<TextView>(R.id.event_date).text = currentEvent.date
            findViewById<TextView>(R.id.event_time).text = currentEvent.time
            findViewById<TextView>(R.id.event_duration).text = currentEvent.durationInHour.toString()
            findViewById<Button>(R.id.register_button).setOnClickListener {
                val intent = Intent(context, EventRegisterActivity::class.java)
                intent.putExtra("event", currentEvent)
                context.startActivity(intent)
            }
            if(currentUser?.isAdmin == true) {
                this.setOnClickListener() {
                    val intent = Intent(context, DeleteEventActivity::class.java)
                    intent.putExtra("event", currentEvent)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}