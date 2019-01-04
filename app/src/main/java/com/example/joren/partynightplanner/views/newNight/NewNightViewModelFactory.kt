package com.example.joren.partynightplanner.views.newNight

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.events.MyEventRepo

class NewNightViewModelFactory(private val eventRepo: MyEventRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewNightViewModel(eventRepo) as T
    }
}