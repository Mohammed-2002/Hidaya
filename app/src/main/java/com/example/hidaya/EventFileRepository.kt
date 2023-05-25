package com.example.hidaya

import com.google.firebase.database.*


class  EventFileRepository (){

    companion object {
        private val eventsRef: DatabaseReference = FirebaseDatabase.getInstance("https://hidaya-d99de-default-rtdb.europe-west1.firebasedatabase.app").getReference("Events")

        fun getEvents(callback: (ArrayList<Event>) -> Unit) {
            eventsRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val children = ArrayList<Event>()
                    for (childSnapshot in snapshot.children) {
                        val event = childSnapshot.getValue(Event::class.java)
                        event?.let {
                            children.add(it)
                        }
                    }
                    callback(children)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(ArrayList())
                }
            })
        }
        fun getEvent(name: String, callback: (Event?) -> Unit) {
            eventsRef.child(name).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val event = snapshot.getValue(Event::class.java)
                    callback(event)
                }
                override fun onCancelled(error: DatabaseError) {
                    callback(null)
                }
            })
        }

        fun saveEvent(event: Event) {
            eventsRef.child(event.eventSubject).setValue(event)
        }
        fun removeEvent(event: Event){
            eventsRef.child(event.eventSubject).removeValue()
        }
    }
}