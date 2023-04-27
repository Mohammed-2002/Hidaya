package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hidaya.databinding.ActivityEventsBinding
import com.example.hidaya.databinding.ActivityNewUserBinding

class NewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inschrijfbuton.setOnClickListener(){
            val name = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            if(name == "" || email == "" || password == "" ){
                Toast.makeText(this, "Vul Alle velden in", Toast.LENGTH_LONG).show()
            }
            else{
                val userFileRepository = UserFileRepository(this)
                UserManger.currentUser = User(name,email,password)
                val userList = userFileRepository.load().toMutableList()
                userList.add(User(name,email,password))
                userFileRepository.save(userList)
                val intent = Intent(this, EventsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}