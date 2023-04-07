

package com.example.hidaya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hidaya.databinding.FragmentLoginBinding

class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding
    lateinit var loginBtn: Button
    lateinit var nameEt : EditText
    lateinit var passwordET : EditText
    private lateinit var callbackFragment: CallbackFragment

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

        val loginBtn = binding.btnLogin
        val nameEt = binding.etName
        val passwordET = binding.etPassword


        loginBtn.setOnClickListener {

            if(nameEt.text.toString() == ""){
                Toast.makeText(
                    requireContext(),
                    "Enter your username",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else if (passwordET.text.toString() == ""){
                Toast.makeText(
                    requireContext(),
                    "Enter your password",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                val bundle = Bundle()
                bundle.putString("name", nameEt.text.toString())
                callbackFragment.changeFragment(bundle)
            }

        }
    }

    fun setCallbackFragment(cbFragment: CallbackFragment){
        callbackFragment = cbFragment
    }
}
