package com.example.hidaya

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.FileReader



class  EventFileRepository (val context: Context): Repository <Event> {

    override fun load(): List<Event> {
        return try{
            val where: File = context.filesDir
            val fileName = where.absolutePath + "/eventsFile.txt"
            val gson = Gson()
            val eventListType = object : TypeToken<List<Event>>(){}.type
            val model = gson.fromJson<List<Event>>(FileReader(fileName),eventListType)
            model
        } catch (e: FileNotFoundException){
            emptyList()
        }
    }

    override fun save(items: List<Event>) {
        val fileName = "eventsFile.txt"
        val fos: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(items)
        fos.write(json.toByteArray())
        fos.close()
    }
}