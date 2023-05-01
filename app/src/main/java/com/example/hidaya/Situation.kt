package com.example.hidaya

import kotlinx.serialization.Serializable

@Serializable
data class Situation(val isLogged: Boolean,val loggedUser: User?) : java.io.Serializable
