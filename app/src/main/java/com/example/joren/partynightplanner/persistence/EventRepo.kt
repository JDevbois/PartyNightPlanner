package com.example.joren.partynightplanner.persistence

import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.util.DummyData

class EventRepo {
    companion object {
        //TODO
        private fun getEventsByName(query: String): Array<Event>{
            return DummyData.getEvents()
        }

        //TODO
        private fun getEventsByOrganiser(query: String): Array<Event>{
            return DummyData.getEvents()
        }

        //TODO
        private fun getEventsByDate(query: String): Array<Event>{
            return DummyData.getEvents()
        }
        fun getAllEvents(): Array<Event> {
            return DummyData.getEvents()
        }

        fun getFilteredEvents(option: Int, query: String): Array<Event> {
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
                else -> DummyData.getEvents()
            }
        }

        const val BY_DATE = 0
        const val BY_NAME = 1
        const val BY_ORGANISER = 2
    }
}