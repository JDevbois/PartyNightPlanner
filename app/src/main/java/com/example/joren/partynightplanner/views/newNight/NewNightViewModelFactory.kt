package com.example.joren.partynightplanner.views.newNight

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.events.EventRepo

class NewNightViewModelFactory(private val eventRepo: EventRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewNightViewModel(eventRepo) as T
    }
}