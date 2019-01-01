package com.example.joren.partynightplanner.persistence.events

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.joren.partynightplanner.domain.Event
import java.text.SimpleDateFormat
import java.util.*

class EventDao {
    private val eventList = mutableListOf<Event>()
    private val events = MutableLiveData<List<Event>>()

    init {
        events.value = eventList
    }

    fun addEvent(event: Event){
        eventList.add(event)
        events.value = eventList
    }

    fun getEvents() = events as LiveData<List<Event>>

    companion object {
        const val BY_DATE = 0
        const val BY_NAME = 1
        const val BY_ORGANISER = 2
    }
}