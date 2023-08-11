package com.example.devicesinventory.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.devicesinventory.LoginActivity
import com.example.devicesinventory.MainActivity
import com.example.devicesinventory.R
import com.example.devicesinventory.data.DatabaseOperator
import com.example.devicesinventory.user.User
import com.example.devicesinventory.databinding.LoginFormfragmentBinding
import com.example.devicesinventory.user.AdaptedUser
import com.google.android.material.snackbar.Snackbar

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

    fun toLogin(){
        binding.loginfrgTvLogin.visibility = View.VISIBLE
        binding.loginfrgTvSignup.visibility=View.GONE
        binding.loginfrgEtUsername.visibility=View.GONE
        binding.loginfrgEtConfirmPassword.visibility=View.GONE
        binding.loginfrgBtSubmit.text="LOGIN"
        binding.loginfrgBlockToRegister.visibility=View.VISIBLE
        binding.loginfrgBlockToLogin.visibility=View.GONE
    }

    fun toSignup(){
        binding.loginfrgTvLogin.visibility=View.GONE
        binding.loginfrgTvSignup.visibility=View.VISIBLE
        binding.loginfrgEtUsername.visibility=View.VISIBLE
        binding.loginfrgEtConfirmPassword.visibility=View.VISIBLE
        binding.loginfrgBtSubmit.text= "SIGNUP"
        binding.loginfrgBlockToRegister.visibility=View.GONE
        binding.loginfrgBlockToLogin.visibility=View.VISIBLE
    }

    fun login(){
        Snackbar.make(currentView,"button pressed",Snackbar.LENGTH_SHORT).show()
        var password: String = binding.loginfrgEtPassword.text.toString()
        var user: User = DatabaseOperator.db.userDAO().get(binding.loginfrgEtEmail.text.toString())
        if(user.password.toString() == password) logged(user)
        else {
            binding.loginfrgEtPassword.setText("")
            Snackbar.make(currentView,"Password missmatch",Snackbar.LENGTH_SHORT).show()
        }

    }

    fun logged(user: User){
        val intent =  Intent(activity,MainActivity::class.java)
        intent.putExtra("loggedUser",AdaptedUser(user))
        activity.startActivity(intent)
        activity.finish()
    }

}