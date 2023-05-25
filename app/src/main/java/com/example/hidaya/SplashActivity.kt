package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isUserLoggedIn()

        finish()
    }

    private fun isUserLoggedIn() {
        val sharedPref = getSharedPreferences("my_prefs", MODE_PRIVATE)
        val json = sharedPref.getString("Situation", "")
        val gson = Gson()
        val situation = gson.fromJson(json, Situation::class.java)
        if(situation != null){
            if(situation.isLogged){
                situation.loggedUserEmail?.let {
                    UserFileRepository.getUser(it){toGetUser ->
                        UserManger.currentUser = toGetUser
                        startActivity(Intent(this, EventsActivity::class.java))
                    }
                }
            }
        }
        startActivity(Intent(this, LoginActivity::class.java))

    }
}
