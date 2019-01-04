package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.MainViewModelFactory
import com.example.joren.partynightplanner.persistence.Database
import com.example.joren.partynightplanner.persistence.nights.MyNightRepo
import com.example.joren.partynightplanner.views.plannedNights.PlannedNightsViewModelFactory

object InjectorUtils {

    fun providePlannedNightsViewModelFactory(): PlannedNightsViewModelFactory{
        val nightRepo = MyNightRepo.getInstance(Database.getInstance().nightDao)
        return PlannedNightsViewModelFactory(nightRepo)
    }

    fun provideMainViewModelFactory(): MainViewModelFactory{
        val nightRepo = MyNightRepo.getInstance(Database.getInstance().nightDao)
        return MainViewModelFactory(nightRepo)
    }
}