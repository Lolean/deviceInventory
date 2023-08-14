package com.example.devicesinventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.devicesinventory.databinding.ActivityMenuBinding
import com.example.devicesinventory.databinding.MainMenufragmentBinding
import com.example.devicesinventory.device.AdaptedDevice
import com.example.devicesinventory.fragment.DeviceRecycleViewFragment
import com.example.devicesinventory.fragment.DeviceShowFragment
import com.example.devicesinventory.fragment.MainMenuFragment
import com.example.devicesinventory.user.AdaptedUser

class MainActivity : AppCompatActivity() {

    lateinit var bindingActivity: ActivityMenuBinding
    lateinit var bindingMenu: MainMenufragmentBinding
    var deviceShowFragment = DeviceShowFragment()
    private var menuFragment = MainMenuFragment()
    private var deviceList = DeviceRecycleViewFragment()
    lateinit var user : AdaptedUser
    lateinit var chosenDevice : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        bindingActivity = ActivityMenuBinding.inflate(layoutInflater)
        bindingMenu = MainMenufragmentBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_menu)
        setFragment(menuFragment,false)
        user = intent.getSerializableExtra("loggedUser") as AdaptedUser


    }

    fun menuPress(view: View){
        when(view.id){
            bindingMenu.menuBtDevices.id -> setFragment(deviceList,true)
            bindingMenu.menuBtUsers.id -> {}
        }
    }


    fun setFragment(frg: Fragment, backstack: Boolean){

        supportFragmentManager.beginTransaction().apply {
            replace(bindingActivity.mainFlFragment.id, frg)
            if(backstack) addToBackStack(null)
            commit()
        }

    }
}