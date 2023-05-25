package com.example.hidaya

import kotlinx.serialization.Serializable
import java.time.LocalTime
import java.util.Date

@Serializable

data class Event(val eventSubject: String, val date: String, val time: String, val durationInHour: String, val type: TypeEvent,var usersMails: MutableList<String>) : java.io.Serializable{
    constructor() : this("", "", "", "", TypeEvent.LEZING, mutableListOf())
}

