package com.example.joren.partynightplanner

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.nights.MyNightRepo
import com.example.joren.partynightplanner.views.plannedNights.PlannedNightsViewModel

class MainViewModelFactory(private val nightRepo: MyNightRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(nightRepo) as T
    }
}