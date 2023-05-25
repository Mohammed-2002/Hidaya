package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hidaya.databinding.ActivityWordAdminBinding

class WordAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var currentUser = UserManger.currentUser
        binding.btnWordAdmin.setOnClickListener(){
            val ingevuldePassword = binding.etAdminPassword.text.toString()
            if(ingevuldePassword == "IkBenAdmin") {
                if (currentUser != null) {
                    UserFileRepository.getUser(currentUser.email){userToUpdate ->
                        userToUpdate?.isAdmin = true
                        if (userToUpdate != null) {
                            UserFileRepository.saveUser(userToUpdate)
                        }
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        this.startActivity(intent)
                    }
                }
            }
            else{
                Toast.makeText(this, "wachtwoord is niet correct", Toast.LENGTH_SHORT).show()
            }
        }
    }
}