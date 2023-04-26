package com.example.hidaya


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TypeEventFilterAdapter(val events: List<TypeEventFilter>): RecyclerView.Adapter<TypeEventFilterAdapter.TypeEventFilterViewHolder>(){


        inner class TypeEventFilterViewHolder(currentItemView: View) : RecyclerView.ViewHolder(currentItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeEventFilterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_typeevent, parent, false)
        return TypeEventFilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeEventFilterAdapter.TypeEventFilterViewHolder, position: Int) {
        val currentEvent = events[position]
        holder.itemView.apply{
            findViewById<TextView>(R.id.event_subject).text = currentEvent.type.toString()
            val checkBox = findViewById<CheckBox>(R.id.event_checkbox)
            checkBox.isChecked = currentEvent.geselecteerd

            checkBox.setOnClickListener(){
            }
            checkBox.id = View.generateViewId()
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}