package com.example.hidaya
import kotlinx.serialization.Serializable


@Serializable
data class User (val name: String, val email: String, val password: String):java.io.Serializable