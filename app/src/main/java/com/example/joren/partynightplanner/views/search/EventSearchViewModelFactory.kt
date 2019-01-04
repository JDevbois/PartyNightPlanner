package com.example.joren.partynightplanner.views.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.events.MyEventRepo

class EventSearchViewModelFactory(private val eventRepo: MyEventRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventSearchViewModel(eventRepo) as T
    }
}