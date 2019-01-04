package com.example.joren.partynightplanner.persistence.events

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import java.text.SimpleDateFormat
import java.util.*

class EventRepo private constructor(private val eventDao: EventDao){

    // CRUD OPERATIONS
    fun addEvent(event: Event){
        eventDao.addEvent(event)
    }

    fun getEvents() = eventDao.getEvents()

    fun deleteEvent(event: Event){
        eventDao.deleteEvent(event)
    }

    fun updateEvent(event: Event){
        eventDao.updateEvent(event)
    }

    private fun getEventsByName(query: String): LiveData<List<Event>>{
        return Transformations.map(getEvents()){events -> events.filter { e -> e.title.contains(query) }}
    }

    private fun getEventsByOrganiser(query: String): LiveData<List<Event>>{
        return Transformations.map(getEvents()){events -> events.filter { e -> e.organiser.contains(query) }}
    }

    private fun getEventsByDate(query: String): LiveData<List<Event>>{
        return Transformations.map(getEvents()){events -> events.filter { e -> SimpleDateFormat("MM/dd/yy", Locale.US).format(e.startDate) == query }}
    }

    fun getFilteredEvents(option: Int, query: String): LiveData<List<Event>> {
        return when(option){
            BY_NAME -> {
                getEventsByName(query)
            }
            BY_ORGANISER -> {
                getEventsByOrganiser(query)
            }
            BY_DATE -> {
                getEventsByDate(query)
            }
            else -> Transformations.map(getEvents()){events -> events.sortedBy { e -> e.startDate.time }}
        }
    }

    fun getAddableEventsForNight(night: Night): LiveData<List<Event>> {
        // return getEvents().value!!.filter { e -> e.endDate.after(time) }
        //TODO filter out events that are already in night
        return Transformations.map(getEvents()){events ->
            events.filter { e ->
                e.endDate.after(night.date)
            }
        }
    }

    companion object {
        // singleton code
        @Volatile private var instance: EventRepo? = null

        fun getInstance(eventDao: EventDao) =
                instance
                        ?: synchronized(this){
                    instance
                            ?: EventRepo(eventDao).also {
                        instance = it
                    }
                }

        const val BY_DATE = 0
        const val BY_NAME = 1
        const val BY_ORGANISER = 2
    }
}