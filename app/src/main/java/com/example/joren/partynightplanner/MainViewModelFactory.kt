package com.example.joren.partynightplanner

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.events.MyEventRepo
import com.example.joren.partynightplanner.persistence.nights.NightRepo

class MainViewModelFactory(private val nightRepo: NightRepo, private val eventRepo: MyEventRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(nightRepo, eventRepo) as T
    }
}