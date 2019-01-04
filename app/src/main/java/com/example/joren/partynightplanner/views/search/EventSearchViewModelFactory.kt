package com.example.joren.partynightplanner.views.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.events.EventRepo

class EventSearchViewModelFactory(private val eventRepo: EventRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventSearchViewModel(eventRepo) as T
    }
}