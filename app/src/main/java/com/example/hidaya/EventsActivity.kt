package com.example.hidaya

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hidaya.databinding.ActivityEventsBinding
import com.example.hidaya.UserManger.currentUser
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import org.checkerframework.checker.units.qual.Current
import java.io.ByteArrayOutputStream


class EventsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventsBinding
    private lateinit var menuBarToggle: ActionBarDrawerToggle
    private lateinit var selectedEvents: List<Event>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        EventFileRepository.getEvents { events ->
            selectedEvents = getListOfSelectedEvents(events)
            var adapter = EventsAdapter(getListOfSelectedEvents(events))
            binding.eventsRecycleView.adapter = adapter
            binding.eventsRecycleView.layoutManager = LinearLayoutManager(this)

            binding.checkbox1.setOnCheckedChangeListener { _, _ ->

                selectedEvents = getListOfSelectedEvents(events)
                adapter = EventsAdapter(selectedEvents)
                binding.eventsRecycleView.adapter = adapter
            }

            binding.checkbox2.setOnCheckedChangeListener { _, _ ->
                selectedEvents = getListOfSelectedEvents(events)
                adapter = EventsAdapter(selectedEvents)
                binding.eventsRecycleView.adapter = adapter
            }
        }
        val currentUser = UserManger.currentUser
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val headerView = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.name).text = currentUser?.name
        headerView.findViewById<TextView>(R.id.email).text = currentUser?.email

        menuBarToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.menu_open, R.string.menu_close)
        binding.drawerLayout.addDrawerListener(menuBarToggle)
        menuBarToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView= findViewById<NavigationView>(R.id.navigationView)
        val headView = navView.getHeaderView(0)
        val imageView = headView.findViewById<ImageView>(R.id.profile_image)
        if (currentUser?.photoBytes != null) {
            val decodedBytes = Base64.decode(currentUser.photoBytes, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
            imageView.setImageBitmap(bitmap)
        } else {
            imageView.setImageResource(R.drawable.user)
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.change_photo -> {
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(takePictureIntent, 1)
                }
                R.id.word_admin -> {
                    if(currentUser?.isAdmin == true){
                        Toast.makeText(this, "U bent al Admin", Toast.LENGTH_SHORT).show()
                    }else {
                        val intent = Intent(this, WordAdminActivity::class.java)
                        this.startActivity(intent)
                    }
                }
                R.id.menu_logout -> {
                    val gson = Gson()
                    val situation = Situation(false,null)
                    val situationInJson = gson.toJson(situation)
                    val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                    sharedPref.edit().putString("Situation", situationInJson).apply()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    this.startActivity(intent)
                }
            }
            true
        }


        binding.btnAddEvent.setOnClickListener(){
            val intent = Intent(this, AddNewEventActivity::class.java)
            this.startActivity(intent)
        }
        if(currentUser?.isAdmin == false) {
            binding.btnAddEvent.visibility = View.GONE
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val imageByteArray = byteArrayOutputStream.toByteArray()

            currentUser?.photoBytes = Base64.encodeToString(imageByteArray, Base64.DEFAULT)
            currentUser?.email?.let { UserFileRepository.saveUser(currentUser!!) }
            recreate()

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // we need to do this to respond correctly to clicks on menu items, otherwise it won't be caught
        if(menuBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListOfSelectedEvents(events: List<Event>) : List<Event> {
        var list = mutableListOf<Event>()
        events.forEach(){event ->
            if(binding.checkbox1.isChecked && event.type == TypeEvent.FUN){
                list.add(event)
            }
            if(binding.checkbox2.isChecked && event.type == TypeEvent.LEZING ){
                list.add(event)
            }
        }
        return list
    }



}