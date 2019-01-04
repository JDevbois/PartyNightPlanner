package com.example.joren.partynightplanner.views.plannedNights

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.nights.MyNightRepo

class PlannedNightsViewModelFactory(private val nightRepo: MyNightRepo) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlannedNightsViewModel(nightRepo) as T
    }
}