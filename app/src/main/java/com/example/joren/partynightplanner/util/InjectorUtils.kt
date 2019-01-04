package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.MainViewModelFactory
import com.example.joren.partynightplanner.persistence.Database
import com.example.joren.partynightplanner.persistence.events.MyEventRepo
import com.example.joren.partynightplanner.persistence.nights.NightRepo
import com.example.joren.partynightplanner.views.newNight.NewNightViewModelFactory
import com.example.joren.partynightplanner.views.plannedNights.PlannedNightsViewModelFactory

object InjectorUtils {

    fun providePlannedNightsViewModelFactory(): PlannedNightsViewModelFactory{
        val nightRepo = NightRepo.getInstance(Database.getInstance().nightDao)
        return PlannedNightsViewModelFactory(nightRepo)
    }

    fun provideMainViewModelFactory(): MainViewModelFactory{
        val nightRepo = NightRepo.getInstance(Database.getInstance().nightDao)
        val eventRepo = MyEventRepo.getInstance(Database.getInstance().eventDao)
        return MainViewModelFactory(nightRepo, eventRepo)
    }

    fun provideNewNightViewModelFactory(): NewNightViewModelFactory{
        val eventRepo = MyEventRepo.getInstance(Database.getInstance().eventDao)
        return NewNightViewModelFactory(eventRepo)
    }
}