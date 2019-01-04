package com.example.joren.partynightplanner.persistence

import android.util.Log
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.events.EventDao
import com.example.joren.partynightplanner.persistence.nights.NightDao
import com.example.joren.partynightplanner.util.DummyData.Companion.events
import com.example.joren.partynightplanner.util.DummyData.Companion.nights
import com.google.firebase.database.*

class Database private constructor(){
    val eventDao: EventDao = EventDao()
    val nightDao: NightDao = NightDao()

    companion object {

        val firebaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
        val eventCloudEndPoint:DatabaseReference = firebaseReference.child("events")
        val nightCloudEndPoint:DatabaseReference = firebaseReference.child("nights")

        // singleton code
        @Volatile private var instance: Database? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance
                        ?: Database().also {
                    instance = it
                }
            }
    }
}