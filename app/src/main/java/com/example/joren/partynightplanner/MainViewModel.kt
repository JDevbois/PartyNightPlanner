package com.example.joren.partynightplanner

import android.arch.lifecycle.ViewModel
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.events.EventRepo
import com.example.joren.partynightplanner.persistence.nights.NightRepo

class MainViewModel(private val nightRepo: NightRepo, private val eventRepo: EventRepo): ViewModel() {
    fun addNight(night: Night){
        nightRepo.addNight(night)
    }

    // TODO only events after current date
    fun getUpcomingEvents() = eventRepo.getEvents()
}