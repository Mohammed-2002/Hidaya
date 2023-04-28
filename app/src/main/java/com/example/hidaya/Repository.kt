package com.example.hidaya

interface Repository {

    fun load(): List<User>

    fun save(items: List<User>)

}