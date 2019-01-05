package com.example.joren.partynightplanner.views.plannedNights

import android.arch.lifecycle.ViewModel
import com.example.joren.partynightplanner.persistence.nights.NightRepo

class PlannedNightsViewModel(private val nightRepo: NightRepo): ViewModel() {
    fun getNightsForUser(id: String) = nightRepo.getNightsByUser(id)
}