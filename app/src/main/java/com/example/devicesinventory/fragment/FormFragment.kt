package com.example.devicesinventory.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.devicesinventory.R
import com.example.devicesinventory.databinding.LoginFormfragmentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormFragment : Fragment(R.layout.login_formfragment) {

    private lateinit var binding: LoginFormfragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFormfragmentBinding.bind(view)
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
        binding.loginfrgBtSubmit.text="SIGNUP"
        binding.loginfrgBlockToRegister.visibility=View.GONE
        binding.loginfrgBlockToLogin.visibility=View.VISIBLE
    }

}