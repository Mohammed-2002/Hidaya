package com.example.hidaya

interface Repository<T> {

    fun load(): List<T>

    fun save(items: List<T>)
}