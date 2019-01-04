package com.example.joren.partynightplanner.persistence.nights

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.Database
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class NightDao {
    private val nightList = mutableListOf<Night>()
    private val nights = MutableLiveData<List<Night>>()

    init {
        val nightListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                refreshNights(p0)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.i("Firebase","loadPost:onCancelled ${p0.toException()}")
            }

        }

        Database.nightCloudEndPoint.addValueEventListener(nightListener)
        nights.value = nightList
    }

    fun refreshNights(p0: DataSnapshot){
        nightList.clear()
        p0.children.mapNotNullTo(nightList){
            it.getValue<Night>(Night::class.java)
        }
        nights.value = nightList

        // TODO: debugging purposes only, remove for release
        Log.i("DB", nightList.map { n -> n.name }.joinToString(separator = ", "))
    }

    // CRUD OPERATIONS
    fun addNight(n: Night){
        val key = Database.nightCloudEndPoint.push().key
        if (key != null){
            Database.nightCloudEndPoint.child(key).setValue(n.also { n.id = key })
        }
    }

    fun getNights() = nights as LiveData<List<Night>>

    fun deleteNight(n: Night) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun updateNight(n: Night) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}