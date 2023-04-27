package com.example.hidaya

interface UserRepository {

    fun load(): List<User>

    fun save(items: List<User>)

}