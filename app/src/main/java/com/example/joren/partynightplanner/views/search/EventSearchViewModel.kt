package com.example.joren.partynightplanner.views.search

import android.arch.lifecycle.ViewModel
import com.example.joren.partynightplanner.persistence.events.EventRepo

class EventSearchViewModel(private val eventRepo: EventRepo): ViewModel() {
    fun getFilteredEvents(option: Int, query: String) = eventRepo.getFilteredEvents(option, query)
}