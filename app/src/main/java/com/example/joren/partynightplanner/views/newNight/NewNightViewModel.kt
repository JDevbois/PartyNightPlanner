package com.example.joren.partynightplanner.views.newNight

import android.arch.lifecycle.ViewModel
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.events.EventRepo

class NewNightViewModel(private val eventRepo: EventRepo): ViewModel() {
    var night: Night? = null

    fun parseNight(p0: Night){
        night = p0
    }

    fun getAvailableEvents() = eventRepo.getAddableEventsForNight(night!!)
}