package com.example.hidaya

import kotlinx.serialization.Serializable
import java.time.LocalTime
import java.util.Date

@Serializable

data class Event(val eventSubject: String, val date: String, val time: String, val durationInHour: Double, val type: TypeEvent) : java.io.Serializable{

}
