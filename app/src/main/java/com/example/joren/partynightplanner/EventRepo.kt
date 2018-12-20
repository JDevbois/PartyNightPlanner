package com.example.joren.partynightplanner

import com.example.joren.partynightplanner.util.DummyData

class EventRepo {
    companion object {
        fun getAllEvents(): Array<Event> {
            return DummyData.getEvents()
        }

        fun getFilteredEvents(): Array<Event> {
            // TODO
            return DummyData.getEvents()
        }
    }
}