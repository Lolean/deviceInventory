package com.example.devicesinventory

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.devicesinventory.data.Database
import com.example.devicesinventory.data.DatabaseOperator
import com.example.devicesinventory.databinding.ActivityLoginBinding
import com.example.devicesinventory.databinding.LoginFormfragmentBinding
import com.example.devicesinventory.databinding.LoginHomefragmentBinding
import com.example.devicesinventory.fragment.FormFragment
import com.example.devicesinventory.fragment.HomeFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class LoginActivity: AppCompatActivity() {

    private var homeFragment = HomeFragment()
    private var formFragment = FormFragment()
    private lateinit var bindingForm : LoginFormfragmentBinding
    private lateinit var bindingHome : LoginHomefragmentBinding
    private lateinit var bindingActivity : ActivityLoginBinding

    //usual view biding and layout inflate
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        DatabaseOperator.startDatabase(applicationContext)
        bindingActivity = ActivityLoginBinding.inflate(layoutInflater)
        bindingHome = LoginHomefragmentBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)
        setFragment(formFragment,true)
        bindingForm = LoginFormfragmentBinding.inflate(layoutInflater)
    }

    // Button switch
    fun press(v: View){
        when(v.id){
            bindingHome.loginfrgBtLogin.id -> setFragment(formFragment,true)
            bindingHome.loginfrgBtSignup.id -> setFragment(formFragment, true)
            bindingForm.loginfrgBtToRegister.id -> formFragment.toSignup()
            bindingForm.loginfrgBtToLogin.id -> formFragment.toLogin()
            bindingForm.boutonlogin.id -> formFragment.login()
            bindingForm.boutonsignup.id -> formFragment.register()
        }
    }

    // fragment loading function
    private fun setFragment(frg: Fragment, backstack: Boolean){

        supportFragmentManager.beginTransaction().apply {
            replace(bindingActivity.loginFlfragment.id, frg)
            if(backstack) addToBackStack(null)
            commit()
        }
    }


}