package com.example.joren.partynightplanner.persistence.events

import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.util.DummyData
import java.text.SimpleDateFormat
import java.util.*

class MyEventRepo private constructor(private val eventDao: EventDao){

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

    // TODO: move from down here onwards to respective viewmodels with livedata instead of normal List<Event>

    private fun getEventsByName(query: String): List<Event>{
        return getEvents().value!!.filter { e -> e.title.contains(query) }
    }

    private fun getEventsByOrganiser(query: String): List<Event>{
        return getEvents().value!!.filter { e -> e.organiser.contains(query) }
    }

    private fun getEventsByDate(query: String): List<Event>{
        return getEvents().value!!.filter { e -> SimpleDateFormat("MM/dd/yy", Locale.US).format(e.startDate) == query }
    }

    fun getFilteredEvents(option: Int, query: String): List<Event> {
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
            else -> getEvents().value!!.sortedBy { e -> e.startDate.time }
        }
    }

    fun getEventsAfter(time: Date?): List<Event> {
        return getEvents().value!!.filter { e -> e.endDate.after(time) }
    }

    companion object {
        // singleton code
        @Volatile private var instance: MyEventRepo? = null

        fun getInstance(eventDao: EventDao) =
                instance
                        ?: synchronized(this){
                    instance
                            ?: MyEventRepo(eventDao).also {
                        instance = it
                    }
                }

        const val BY_DATE = 0
        const val BY_NAME = 1
        const val BY_ORGANISER = 2
    }
}