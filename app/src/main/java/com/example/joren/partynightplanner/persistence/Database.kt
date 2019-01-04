package com.example.joren.partynightplanner.persistence

import android.util.Log
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.events.EventDao
import com.example.joren.partynightplanner.persistence.nights.NightDao
//TODO: should move to actual repo
import com.example.joren.partynightplanner.util.DummyData.Companion.events
import com.example.joren.partynightplanner.util.DummyData.Companion.nights
import com.google.firebase.database.*

class Database private constructor(){
    val eventDao: EventDao = EventDao()
    val nightDao: NightDao = NightDao()


    /*
    fun writeNewNight(night: Night){
        val key = database.child("nights").push().key
        if (key != null) {
            database.child("nights").child(key).setValue(night)
        }
    }
    */
    //TODO: Testing purposes only
    fun loadDummyDataToDB(){
        for (e: Event in events){
            val key = eventCloudEndPoint.push().key
            if (key != null){
                eventCloudEndPoint.child(key).setValue(e.also { e.id = key })
            }
        }

        for (n: Night in nights){
            val key = nightCloudEndPoint.push().key
            if (key != null){
                nightCloudEndPoint.child(key).setValue(n.also { n.id = key })
            }
        }
    }

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