package com.example.devicesinventory.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.devicesinventory.LoginActivity
import com.example.devicesinventory.MainActivity
import com.example.devicesinventory.R
import com.example.devicesinventory.data.DatabaseOperator
import com.example.devicesinventory.data.FieldConstraint
import com.example.devicesinventory.user.User
import com.example.devicesinventory.databinding.LoginFormfragmentBinding
import com.example.devicesinventory.user.AdaptedUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * A simple [Fragment] subclass.
 * Use the [FormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormFragment : Fragment(R.layout.login_formfragment) {

    private lateinit var binding: LoginFormfragmentBinding
    private lateinit var currentView: View
    private lateinit var activity: Activity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFormfragmentBinding.bind(view)
        currentView = view
        activity = requireActivity() as LoginActivity
    }
    // UI functions
    fun toLogin(){
        requireActivity().runOnUiThread{
            binding.loginfrgTvLogin.visibility = View.VISIBLE
            binding.loginfrgTvSignup.visibility = View.GONE
            binding.loginfrgEtUsername.visibility = View.GONE
            binding.loginfrgEtConfirmPassword.visibility = View.GONE
            binding.boutonlogin.visibility = View.VISIBLE
            binding.boutonsignup.visibility = View.GONE
            binding.loginfrgBlockToRegister.visibility = View.VISIBLE
            binding.loginfrgBlockToLogin.visibility = View.GONE
        }

    }

    fun toSignup(){
        requireActivity().runOnUiThread {
            binding.loginfrgTvLogin.visibility=View.GONE
            binding.loginfrgTvSignup.visibility=View.VISIBLE
            binding.loginfrgEtUsername.visibility=View.VISIBLE
            binding.loginfrgEtConfirmPassword.visibility=View.VISIBLE
            binding.boutonsignup.visibility=View.VISIBLE
            binding.boutonlogin.visibility=View.GONE
            binding.loginfrgBlockToRegister.visibility=View.GONE
            binding.loginfrgBlockToLogin.visibility=View.VISIBLE
        }

    }

    // Backend functions
    fun login(){
        CoroutineScope(Dispatchers.IO).launch() {
            var password: String = binding.loginfrgEtPassword.text.toString()
            var user: User? = DatabaseOperator.db.userDAO().get(binding.loginfrgEtEmail.text.toString())
            if(user !=null){
                if(user.password == password) logged(user)
                else {
                    binding.loginfrgEtPassword.setText("")
                    Snackbar.make(currentView,"Wrong password",Snackbar.LENGTH_SHORT).show()
                }
            }
            else {
                binding.loginfrgEtPassword.setText("")
                Snackbar.make(currentView,"User not found",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun register(){
        CoroutineScope(Dispatchers.IO).launch() {
            var checkData: User? = DatabaseOperator.db.userDAO().get(binding.loginfrgEtEmail.text.toString())
            if (checkData?.email == null) {
                var username: String = binding.loginfrgEtUsername.text.toString()
                var email: String = binding.loginfrgEtEmail.text.toString()
                var password: String = binding.loginfrgEtPassword.text.toString()
                var confirmPassword: String = binding.loginfrgEtConfirmPassword.text.toString()
                if (password == confirmPassword) {
                    var user: User =
                        User(0, email,username, password, FieldConstraint.credential.BASIC)
                    DatabaseOperator.db.userDAO().add(user)
                    toLogin()
                    Snackbar.make(currentView, "user created", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    binding.loginfrgEtPassword.setText("")
                    binding.loginfrgEtConfirmPassword.setText("")
                    Snackbar.make(currentView, "Password missmatch", Snackbar.LENGTH_SHORT).show()
                }
            }
            else Snackbar.make(currentView, "User already exist", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun logged(user: User){
        val intent =  Intent(activity,MainActivity::class.java)
        intent.putExtra("loggedUser",AdaptedUser(user))
        activity.startActivity(intent)
        activity.finish()
    }



}