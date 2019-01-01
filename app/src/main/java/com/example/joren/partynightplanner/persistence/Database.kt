package com.example.joren.partynightplanner.persistence

import com.example.joren.partynightplanner.persistence.events.EventDao
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Database private constructor(){
    val EventDao = EventDao()



    /*
    fun writeNewNight(night: Night){
        val key = database.child("nights").push().key
        if (key != null) {
            database.child("nights").child(key).setValue(night)
        }
    }

    fun initDatabase(){
        val eventListener = object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                events.clear()
                p0.children.mapNotNullTo(events){
                    it.getValue<Event>(Event::class.java)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.i("Firebase","loadPost:onCancelled ${p0.toException()}")
            }

        }

        val nightListener = object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                nights.clear()
                p0.children.mapNotNullTo(nights){
                    it.getValue<Night>(Night::class.java)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.i("Firebase","loadPost:onCancelled ${p0.toException()}")
            }

        }

        database.addListenerForSingleValueEvent(eventListener)
        database.addListenerForSingleValueEvent(nightListener)
    }
    */

    companion object {

        private var firebaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

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