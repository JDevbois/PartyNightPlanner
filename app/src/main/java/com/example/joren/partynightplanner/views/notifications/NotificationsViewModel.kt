package com.example.joren.partynightplanner.views.notifications

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.domain.Notification
import com.example.joren.partynightplanner.persistence.notifications.NotificationRepo

class NotificationsViewModel(private val notificationRepo: NotificationRepo): ViewModel() {
    fun getNotificationsForUser(): LiveData<List<Notification>>{
        return notificationRepo.getNotificationsForUser(MainActivity.currUserId)
    }

    fun deleteNotifcation(notification: Notification) {
        notificationRepo.deleteNotification(notification)
    }
}
