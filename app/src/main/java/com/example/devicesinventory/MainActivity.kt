package com.example.devicesinventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.devicesinventory.data.FieldConstraint
import com.example.devicesinventory.databinding.ActivityMenuBinding
import com.example.devicesinventory.databinding.MainMenufragmentBinding
import com.example.devicesinventory.fragment.devicefragments.DeviceAddFragment
import com.example.devicesinventory.fragment.devicefragments.DeviceRecycleViewFragment
import com.example.devicesinventory.fragment.devicefragments.DeviceShowFragment
import com.example.devicesinventory.fragment.MainMenuFragment
import com.example.devicesinventory.fragment.userfragments.UserRecycleViewFragment
import com.example.devicesinventory.user.AdaptedUser

class MainActivity : AppCompatActivity() {

    lateinit var bindingActivity: ActivityMenuBinding
    lateinit var bindingMenu: MainMenufragmentBinding
    var deviceShowFragment = DeviceShowFragment()
    var deviceAddFragment = DeviceAddFragment()
    var menuFragment = MainMenuFragment()
    private var deviceList = DeviceRecycleViewFragment()
    private var userList = UserRecycleViewFragment()
    lateinit var user : AdaptedUser
    var chosenDevice : String? = null
    var fragmentManager : FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = ActivityMenuBinding.inflate(layoutInflater)
        bindingMenu = MainMenufragmentBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(R.layout.activity_menu)
        setFragment(menuFragment,false,"mainmenu")
        user = intent.getSerializableExtra("loggedUser") as AdaptedUser

    }

    override fun onBackPressed() {
        super.onBackPressed()
        setToolbar(fragmentManager.findFragmentById(R.id.main_flFragment)!!)
    }

    //main menu button for device or user section
    fun menuPress(view: View){
        when(view.id){
            bindingMenu.menuBtDevices.id -> setFragment(deviceList,true,"deviceList")
            bindingMenu.menuBtUsers.id -> setFragment(userList,true,"userList")
        }
    }

    //fragment loading in framelayout
    fun setFragment(frg: Fragment, backstack: Boolean,tag : String?) {
        setToolbar(frg)
        fragmentManager.beginTransaction().apply {
            replace(R.id.main_flFragment, frg, tag)
            if (backstack) addToBackStack(null)
            commit()
        }
    }

    //set fragment and remove all fragments in backstack pile
    fun setFragment(frg: Fragment, backstack: Boolean,tag : String?,pop: Boolean){
        setToolbar(frg)

        fragmentManager.beginTransaction().apply {
            bindingActivity.mainFlFragment.removeAllViews()
            replace(R.id.main_flFragment,frg,tag)
            if(backstack) addToBackStack(null)
            commit()
        }
        fragmentManager.popBackStack()
    }

    //return user access to reserved views
    fun isAllowed(): Boolean{
        if(this.user.credential != FieldConstraint.credential.BASIC) return true
        else return false
    }

    //set toolbar button depending on loaded fragment
    fun setToolbar(frg : Fragment){
            when (frg) {
               is DeviceShowFragment -> {
                    findViewById<ImageView>(R.id.main_bt_add).visibility = View.GONE
                    findViewById<ImageView>(R.id.main_bt_back).visibility = View.VISIBLE
                    findViewById<ImageView>(R.id.main_bt_menu).visibility = View.VISIBLE
                    findViewById<ImageView>(R.id.main_bt_back).setOnClickListener {
                        onBackPressed()
                    }

                }
                is DeviceAddFragment -> {
                    findViewById<TextView>(R.id.main_tv_title).text = "add device"
                    findViewById<ImageButton>(R.id.main_bt_add).visibility = View.GONE
                    findViewById<ImageButton>(R.id.main_bt_back).visibility = View.VISIBLE
                    findViewById<ImageButton>(R.id.main_bt_back).setOnClickListener { onBackPressed() }

                }
                is DeviceRecycleViewFragment -> {
                    findViewById<TextView>(R.id.main_tv_title).text = "Device List"
                    findViewById<ImageButton>(R.id.main_bt_add).visibility = View.VISIBLE
                    findViewById<ImageButton>(R.id.main_bt_back).visibility = View.VISIBLE
                    findViewById<ImageButton>(R.id.main_bt_menu).visibility = View.GONE
                    findViewById<ImageButton>(R.id.main_bt_add).setOnClickListener {
                        setFragment(deviceAddFragment, true, "deviceAdd")
                    }
                }
                is MainMenuFragment -> {
                    findViewById<TextView>(R.id.main_tv_title).text = "Main menu"
                    findViewById<ImageButton>(R.id.main_bt_add).visibility = View.GONE
                    findViewById<ImageButton>(R.id.main_bt_back).visibility = View.GONE
                    findViewById<ImageButton>(R.id.main_bt_menu).visibility = View.GONE
                }
            }

    }


}