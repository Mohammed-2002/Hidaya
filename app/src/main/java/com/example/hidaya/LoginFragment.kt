

package com.example.hidaya

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.hidaya.UserFileRepository.Companion.getUser
import com.example.hidaya.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.Gson


class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

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

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext() , gso)

        binding.googleBtn.setOnClickListener{
            signInGoogle();
        }

        binding.btnLogin.setOnClickListener {

            val email = binding.etMail.text.toString()
            val password = binding.etPassword.text.toString()

            if(email.isEmpty()){
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
                getUser(email) { retrievedUser ->
                    var user: User? = retrievedUser

                    if (user != null && user.password == password){
                        UserManger.currentUser = user
                        val gson = Gson()
                        val situation = Situation(true, user.email)
                        val situationInJson = gson.toJson(situation)
                        val sharedPref = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                        sharedPref.edit().putString("Situation", situationInJson).apply()
                        (activity as LoginActivity).showWelcomeFragment()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "email of password zijn niet correct of u bent nog niet ingeschreven",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        binding.registerButton.setOnClickListener(){
            val intent = Intent(context, NewUserActivity::class.java)
            context?.startActivity(intent)
        }
    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(requireContext(), task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val userPhoto = BitmapFactory.decodeResource(resources, R.drawable.user)
                var user:User? = null
                getUser(account.email.toString()) { toGetUser ->
                    user = toGetUser
                    if (user == null) {
                        user = User(
                            account.displayName.toString(),
                            account.email.toString(),
                            "",
                            false,
                            bitmapToBase64(userPhoto)
                        )
                        UserFileRepository.saveUser(user!!)
                    }
                    UserManger.currentUser = user
                    val gson = Gson()
                    val situation = Situation(true, user!!.email)
                    val situationInJson = gson.toJson(situation)
                    val sharedPref =
                        requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                    sharedPref.edit().putString("Situation", situationInJson).apply()
                    (activity as LoginActivity).showWelcomeFragment()
                }
            }else{
                Toast.makeText(requireContext(), it.exception.toString() , Toast.LENGTH_SHORT).show()
            }
        }
    }
}


