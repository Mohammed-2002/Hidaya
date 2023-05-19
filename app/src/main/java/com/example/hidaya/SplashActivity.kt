package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isUserLoggedIn()) {
            startActivity(Intent(this, EventsActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        finish()
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPref = getSharedPreferences("my_prefs", MODE_PRIVATE)
        val json = sharedPref.getString("Situation", "")
        val gson = Gson()
        val situation = gson.fromJson(json, Situation::class.java)
        if(situation != null){
            if(situation.isLogged){
                UserManger.currentUser = situation.loggedUserEmail?.let { getUser(it) }
                return true;
            }
        }
        return false;
    }
    private fun getUser(email: String): User? {
        val userFileRepository = UserFileRepository(this)
        val userList = userFileRepository.load()
        for (user in userList) {
            if (user.email == email) {
                return user
            }
        }
        return null
    }
}
