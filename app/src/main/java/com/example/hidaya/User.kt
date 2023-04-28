package com.example.hidaya
import kotlinx.serialization.Serializable


@Serializable
data class User (val name: String, val email: String, val password: String, var isAdmin: Boolean):java.io.Serializable

var lezing = Event("Reis door de werelden", "12/07/2023", "13:00 uur", 1.50, TypeEvent.LEZING, mutableListOf())
var iftar = Event("Iftar", "12/07/2023", "13:00 uur", 1.50,TypeEvent.FUN, mutableListOf())
var kahoot = Event("kahoot","12/07/2023", "13:00 uur", 3.0, TypeEvent.FUN, mutableListOf())
var lezingOverVatsen = Event("Het belang van vasten", "12/07/2023", "13:00 uur", 1.50,TypeEvent.LEZING, mutableListOf())