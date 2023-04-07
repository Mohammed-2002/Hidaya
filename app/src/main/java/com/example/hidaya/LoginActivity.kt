

package com.example.hidaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.hidaya.databinding.ActivityLoginBinding
import com.example.hidaya.databinding.FragmentLoginBinding

class LoginActivity : AppCompatActivity(), CallbackFragment {

    lateinit var fragment: Fragment
    lateinit var fragmentManager : FragmentManager
    lateinit var fragmentTransaction : FragmentTransaction


    private lateinit var binding: ActivityLoginBinding
     private lateinit var loginBinding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        loginBinding = FragmentLoginBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_login)

        val login = LoginFragment()
        val welcome = WelcomeFragment()
        //val loginBtn = loginBinding.btnLogin

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.loginFC, login)
            addToBackStack(null)
            commit()
        }
        login.setCallbackFragment(this)


        login.view?.findViewById<Button>(R.id.btnLogin)?.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.loginFC, welcome)
                addToBackStack(null)
                commit()
            }
        }

    }


/*    fun addFragment(){
       val fragment = LoginFragment()
       fragment.setCallbackFragment(this)
       fragmentManager = supportFragmentManager
       fragmentTransaction = fragmentManager.beginTransaction()
       fragmentTransaction.add(R.id.loginFC, fragment)
       fragmentTransaction.commit()

   }

     fun replaceFragment(){
       val fragment =  WelcomeFragment()
       fragment.setCallbackFragment(this)
       fragmentManager = supportFragmentManager
       fragmentTransaction = fragmentManager.beginTransaction()
       fragmentTransaction.addToBackStack(null)
       fragmentTransaction.add(R.id.loginFC, fragment)
       fragmentTransaction.commit()

   }*/

    override fun changeFragment(bundle: Bundle?) {
        val welcome = WelcomeFragment()
        welcome.arguments = bundle
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.loginFC, welcome)
            addToBackStack(null)
            commit()
        }
    }
}