package com.example.joren.partynightplanner.views.facebook

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.util.Log
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.domain.User
import com.example.joren.partynightplanner.persistence.nights.NightRepo
import com.example.joren.partynightplanner.persistence.notifications.NotificationRepo
import com.facebook.GraphRequest
import org.json.JSONArray
import org.json.JSONObject

class InviteFriendsViewModel(private val notificationRepo: NotificationRepo, private val nightRepo: NightRepo) : ViewModel() {
    lateinit var night: Night

    fun sendNotificationsForNight(friends: List<String>){
        notificationRepo.addNotification(MainActivity.currUserId, night, friends)
    }

    fun updateNight(night: Night) {
        nightRepo.updateNight(night)
    }
}
