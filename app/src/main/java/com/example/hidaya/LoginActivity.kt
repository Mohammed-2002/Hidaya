

package com.example.hidaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.hidaya.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {




    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginFragment: LoginFragment
    private lateinit var welcomeFragment: WelcomeFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_login)

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
    }
}