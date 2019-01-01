package com.example.joren.partynightplanner.persistence.events

import com.example.joren.partynightplanner.domain.Event

class MyEventRepo private constructor(private val eventDao: EventDao){

    fun addEvent(event: Event){
        eventDao.addEvent(event)
    }

    fun getEvents() = eventDao.getEvents()

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
    }
}