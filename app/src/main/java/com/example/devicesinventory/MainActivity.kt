package com.example.devicesinventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.devicesinventory.databinding.ActivityMenuBinding
import com.example.devicesinventory.databinding.MainMenufragmentBinding
import com.example.devicesinventory.fragment.MainMenuFragment

class MainActivity : AppCompatActivity() {

    lateinit var bindingActivity: ActivityMenuBinding
    lateinit var bindingMenu: MainMenufragmentBinding
    private var menuFragment = MainMenuFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = ActivityMenuBinding.inflate(layoutInflater)
        bindingMenu = MainMenufragmentBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_menu)
        setFragment(menuFragment,false)
    }

    private fun setFragment(frg: Fragment, backstack: Boolean){

        supportFragmentManager.beginTransaction().apply {
            replace(bindingActivity.mainFlFragment.id, frg)
            if(backstack) addToBackStack(null)
            commit()
        }

    }
}