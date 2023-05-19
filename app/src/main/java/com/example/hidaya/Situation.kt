package com.example.hidaya

import kotlinx.serialization.Serializable

@Serializable
data class Situation(val isLogged: Boolean,val loggedUserEmail: String?) : java.io.Serializable
