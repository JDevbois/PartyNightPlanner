package com.example.joren.partynightplanner.views.facebook

import android.arch.lifecycle.ViewModel
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.nights.NightRepo
import com.example.joren.partynightplanner.persistence.notifications.NotificationRepo

class InviteFriendsViewModel(private val notificationRepo: NotificationRepo, private val nightRepo: NightRepo) : ViewModel() {
    fun sendNotificationsForNight(night: Night, friends: List<String>){
        notificationRepo.addNotification(MainActivity.currUserId, night, friends)
    }

    fun updateNight(night: Night) {
        nightRepo.updateNight(night)
    }
}
