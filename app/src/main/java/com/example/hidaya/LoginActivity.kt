

package com.example.hidaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.example.hidaya.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {




    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginFragment: LoginFragment
    private lateinit var welcomeFragment: WelcomeFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loginFragment = LoginFragment()
        welcomeFragment = WelcomeFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.loginFC, loginFragment)
            commit()
        }
    }

     fun showWelcomeFragment(bundle: Bundle?) {
        welcomeFragment = WelcomeFragment()
        welcomeFragment.arguments = bundle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.loginFC, welcomeFragment)
            addToBackStack(null)
            commit()
        }

         val delayMillis = 500L
         Handler().postDelayed({
             val intent = Intent(this, EventsActivity::class.java)
             startActivity(intent)
             finish()
         }, delayMillis)
    }
}