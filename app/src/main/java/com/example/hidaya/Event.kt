package com.example.hidaya

import java.time.LocalTime
import java.util.Date

data class Event(val eventSubject: String, val date: Date, val time: LocalTime, val durationInHour: Double)
