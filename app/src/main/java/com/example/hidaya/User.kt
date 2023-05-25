package com.example.hidaya
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.widget.ImageView
import kotlinx.serialization.Serializable
import java.io.ByteArrayOutputStream


@Serializable
data class User (val name: String, val email: String, val password: String, var isAdmin: Boolean, var photoBytes: String?):java.io.Serializable {
    constructor() : this("", "", "", false, null)

}
fun bitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}




