package com.example.devicesinventory.data

import android.content.Context
import androidx.room.Room
import com.example.devicesinventory.device.Device
import com.example.devicesinventory.user.User
import com.google.android.material.snackbar.Snackbar

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseOperator {

    companion object{
        lateinit var db : Database private set

        fun startDatabase(appContext: Context){
            CoroutineScope(Dispatchers.IO).launch{
                db = Room.databaseBuilder(
                    appContext,
                    Database::class.java,
                    "DeviceInventoryDB").allowMainThreadQueries().build()
                defaultDataSetter()

            }

        }


        fun defaultDataSetter(){

            if(db.deviceDAO().get()!!.isEmpty() && db.userDAO().get()!!.isEmpty()){
                db.userDAO().add(User(0,"admin@device.com","admin","admin",FieldConstraint.credential.ADMIN))
                db.userDAO().add(User(0,"basic@device.com","basic","basic",FieldConstraint.credential.BASIC))
                db.deviceDAO().add(Device("10001/01",FieldConstraint.type.TABLET,"LG","www.LG.com","Gram+view",FieldConstraint.state.AVAILABLE))
                db.deviceDAO().add(Device("20001/01",FieldConstraint.type.PHONE,"Google","www.store.google.com","Pixel 5",FieldConstraint.state.AVAILABLE))
                db.deviceDAO().add(Device("20001/02",FieldConstraint.type.PHONE,"Google","www.store.google.com","Pixel 5",FieldConstraint.state.TAKEN))
            }

        }
    }
}