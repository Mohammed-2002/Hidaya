package com.example.hidaya

import com.google.firebase.database.*
import java.util.*
import android.util.Base64
import android.widget.Toast
import com.google.gson.Gson

class UserFileRepository {
    companion object {
        private val usersRef: DatabaseReference = FirebaseDatabase.getInstance("https://hidaya-d99de-default-rtdb.europe-west1.firebasedatabase.app").getReference("Users")

        fun getUser(email: String, callback: (User?) -> Unit) {
            val sanitizedEmail = email.replace(".", ",")
            usersRef.child(sanitizedEmail).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    callback(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null)
                }
            })
        }

        fun saveUser(user: User) {
            val sanitizedEmail = user.email.replace(".", ",")
            usersRef.child(sanitizedEmail).setValue(user)
        }
    }
}
