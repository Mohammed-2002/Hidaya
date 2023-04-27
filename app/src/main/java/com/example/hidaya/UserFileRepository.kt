package com.example.hidaya

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.FileReader

class  UserFileRepository (val context: Context): UserRepository {

    override fun load(): List<User> {
        return try{
            val where: File = context.filesDir
            val fileName = where.absolutePath + "/usersFile.txt"
            val gson = Gson()
            val userListType = object : TypeToken<List<User>>(){}.type
            val model = gson.fromJson<List<User>>(FileReader(fileName),userListType)
            model
        } catch (e: FileNotFoundException){
            emptyList()
        }
    }

    override fun save(items: List<User>) {
        val fileName = "usersFile.txt"
        val fos:FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(items)
        fos.write(json.toByteArray())
        fos.close()
    }
}