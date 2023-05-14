package com.example.hidaya
import android.graphics.Bitmap
import kotlinx.serialization.Serializable


@Serializable
data class User (val name: String, val email: String, val password: String, var isAdmin: Boolean, var photoBytes: ByteArray?):java.io.Serializable
