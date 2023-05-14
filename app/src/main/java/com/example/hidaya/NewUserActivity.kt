package com.example.hidaya

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hidaya.databinding.ActivityEventsBinding
import com.example.hidaya.databinding.ActivityNewUserBinding
import java.io.ByteArrayOutputStream

class NewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inschrijfbutton.setOnClickListener(){
            val name = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val ingevuldPasswordAdmin = binding.editTextAdminPassword.text.toString()
            val userPhoto = BitmapFactory.decodeResource(resources, R.drawable.user)


            if(name == "" || email == "" || password == "" ){
                Toast.makeText(this, "Vul alle verplichte velden in", Toast.LENGTH_LONG).show()
            }
            else{
                val userFileRepository = UserFileRepository(this)
                val isAdmin = ingevuldPasswordAdmin.equals("IkBenAdmin")
                UserManger.currentUser = User(name,email,password,isAdmin, bitmapToByteArray(userPhoto))
                val userList = userFileRepository.load().toMutableList()
                userList.add(User(name,email,password,isAdmin, bitmapToByteArray(userPhoto)))
                userFileRepository.save(userList)
                val intent = Intent(this, EventsActivity::class.java)
                startActivity(intent)
            }
        }
    }
    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
        return outputStream.toByteArray()
    }
}