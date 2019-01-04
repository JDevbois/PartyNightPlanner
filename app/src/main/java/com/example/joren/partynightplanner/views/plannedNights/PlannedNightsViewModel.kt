package com.example.joren.partynightplanner.views.plannedNights

import android.arch.lifecycle.ViewModel
import com.example.joren.partynightplanner.persistence.nights.MyNightRepo

class PlannedNightsViewModel(private val nightRepo: MyNightRepo): ViewModel() {
    // TODO get by userid
    fun getNightsForUser(id: String) = nightRepo.getNights()
}