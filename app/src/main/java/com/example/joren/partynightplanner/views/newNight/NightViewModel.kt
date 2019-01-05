package com.example.joren.partynightplanner.views.newNight

import android.arch.lifecycle.ViewModel
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.events.EventRepo

class NightViewModel(private val eventRepo: EventRepo): ViewModel() {
    var night: Night? = Night()

    fun parseNight(p0: Night){
        night = p0
        night
    }

    fun getAvailableEvents() = eventRepo.getAddableEventsForNight(night!!)
}