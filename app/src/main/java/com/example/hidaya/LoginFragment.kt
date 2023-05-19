

package com.example.hidaya

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hidaya.databinding.FragmentLoginBinding
import com.google.gson.Gson



class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogin.setOnClickListener {

            val name = binding.etName.text.toString()
            val password = binding.etPassword.text.toString()

            if(name.isEmpty()){
                Toast.makeText(
                    requireContext(),
                    "Enter your username",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else if (password == ""){
                Toast.makeText(
                    requireContext(),
                    "Enter your password",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                val user = getUser(name,password)
                if(user != null){
                    UserManger.currentUser = user
                    val gson = Gson()
                    val situation = Situation(true,user.email)
                    val situationInJson = gson.toJson(situation)
                    val sharedPref = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                    sharedPref.edit().putString("Situation", situationInJson).apply()
                    (activity as LoginActivity).showWelcomeFragment()
                }
                else{
                    Toast.makeText(requireContext(), "email of password zijn niet correct of u bent nog niet ingeschreven", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.registerButton.setOnClickListener(){
            val intent = Intent(context, NewUserActivity::class.java)
            context?.startActivity(intent)
        }
    }

    private fun getUser(email: String, password: String): User? {
        val userFileRepository = UserFileRepository(requireContext())
        val userList = userFileRepository.load()
        for (user in userList) {
            if (user.email == email && user.password == password) {
                return user
            }
        }
        return null
    }

}
