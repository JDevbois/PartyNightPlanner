package com.example.joren.partynightplanner.views.notifications

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.notifications.NotificationRepo

class NotificationsViewModelFactory(private val notificationRepo: NotificationRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotificationsViewModel(notificationRepo) as T
    }
}