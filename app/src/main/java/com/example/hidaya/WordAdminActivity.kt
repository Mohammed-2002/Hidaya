package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hidaya.databinding.ActivityAddNewEventBinding
import com.example.hidaya.databinding.ActivityWordAdminBinding
import java.security.KeyStore.TrustedCertificateEntry

class WordAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var currentUser = UserManger.currentUser
        binding.btnWordAdmin.setOnClickListener(){
            val ingevuldePassword = binding.etAdminPassword.text.toString()
            if(ingevuldePassword.equals("IkBenAdmin")){
                val userRepository = UserFileRepository(this)
                val userList = userRepository.load()
                val userToUpdate = userList.find { it.email == currentUser?.email }
                userToUpdate?.isAdmin = true
                userRepository.save(userList)
                val intent = Intent(this, LoginActivity::class.java)
                this.startActivity(intent)
            }
            else{
                Toast.makeText(this, "Pasword is incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}