package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.MainViewModelFactory
import com.example.joren.partynightplanner.persistence.Database
import com.example.joren.partynightplanner.persistence.events.EventRepo
import com.example.joren.partynightplanner.persistence.nights.NightRepo
import com.example.joren.partynightplanner.persistence.notifications.NotificationRepo
import com.example.joren.partynightplanner.views.facebook.InviteFriendsViewModelFactory
import com.example.joren.partynightplanner.views.newNight.NightViewModelFactory
import com.example.joren.partynightplanner.views.notifications.NotificationsViewModelFactory
import com.example.joren.partynightplanner.views.plannedNights.PlannedNightsViewModelFactory
import com.example.joren.partynightplanner.views.search.EventSearchViewModelFactory

object InjectorUtils {

    fun providePlannedNightsViewModelFactory(): PlannedNightsViewModelFactory{
        val nightRepo = NightRepo.getInstance(Database.getInstance().nightDao)
        return PlannedNightsViewModelFactory(nightRepo)
    }

    fun provideMainViewModelFactory(): MainViewModelFactory{
        val nightRepo = NightRepo.getInstance(Database.getInstance().nightDao)
        val eventRepo = EventRepo.getInstance(Database.getInstance().eventDao)
        return MainViewModelFactory(nightRepo, eventRepo)
    }

    fun provideNightViewModelFactory(): NightViewModelFactory {
        val eventRepo = EventRepo.getInstance(Database.getInstance().eventDao)
        return NightViewModelFactory(eventRepo)
    }

    fun provideEventSearchViewModelFactory(): EventSearchViewModelFactory{
        val eventRepo = EventRepo.getInstance(Database.getInstance().eventDao)
        return EventSearchViewModelFactory(eventRepo)
    }

    fun provideNotificationsViewModelFactory(): NotificationsViewModelFactory{
        val notificationsRepo = NotificationRepo.getInstance(Database.getInstance().notificationDao)
        return NotificationsViewModelFactory(notificationsRepo)
    }

    fun provideInviteFriendsViewModelFactory(): InviteFriendsViewModelFactory {
        val notificationsRepo = NotificationRepo.getInstance(Database.getInstance().notificationDao)
        val nightRepo = NightRepo.getInstance(Database.getInstance().nightDao)
        return InviteFriendsViewModelFactory(notificationsRepo, nightRepo)
    }
}