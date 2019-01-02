package com.example.joren.partynightplanner.persistence.events

import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.util.DummyData
import java.text.SimpleDateFormat
import java.util.*

class EventRepo {
    companion object {

        //TODO move to view model and filter livedata
        private fun getEventsByName(query: String): List<Event>{
            return getAllEvents().filter { e -> e.title.contains(query) }
        }

        //TODO move to view model and filter livedata
        private fun getEventsByOrganiser(query: String): List<Event>{
            return getAllEvents().filter { e -> e.organiser.contains(query) }
        }

        //TODO move to view model and filter livedata
        private fun getEventsByDate(query: String): List<Event>{
            return getAllEvents().filter { e -> SimpleDateFormat("MM/dd/yy", Locale.US).format(e.date) == query }
        }
        fun getAllEvents(): List<Event> {
            return DummyData.events
        }

        //TODO move to view model and filter livedata
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
                else -> getAllEvents()
            }
        }

        fun getEventsAfter(time: Date?): List<Event> {
            return getAllEvents().filter { e -> e.date.before(time) }
        }

        const val BY_DATE = 0
        const val BY_NAME = 1
        const val BY_ORGANISER = 2
    }
}