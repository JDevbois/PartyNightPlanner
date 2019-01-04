package com.example.joren.partynightplanner.persistence.events

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.persistence.Database
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class EventDao {
    private val eventList = mutableListOf<Event>()
    private val events = MutableLiveData<List<Event>>()

    init {
        val eventListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                refreshEvents(p0.children)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.i("Firebase","loadPost:onCancelled ${p0.toException()}")
            }
        }

        Database.eventCloudEndPoint.addValueEventListener(eventListener)
        events.value = eventList
    }

    fun refreshEvents(p0: MutableIterable<DataSnapshot>){
        eventList.clear()
        p0.mapNotNullTo(eventList){
            it.getValue<Event>(Event::class.java)
        }
        events.value = eventList

        // TODO: debugging purposes only, delete for release
        Log.i("DB", eventList.map { e -> e.title }.joinToString(separator = ", "))
    }

    // CRUD OPERATIONS
    fun addEvent(event: Event){
        eventList.add(event)
        events.value = eventList
    }

    fun getEvents() = events as LiveData<List<Event>>

    fun deleteEvent(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun updateEvent(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}