package com.example.joren.partynightplanner.views.facebook

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.nights.NightRepo
import com.example.joren.partynightplanner.persistence.notifications.NotificationRepo

class InviteFriendsViewModelFactory(private val notificationsRepo: NotificationRepo, private val nightRepo: NightRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InviteFriendsViewModel(notificationsRepo, nightRepo) as T
    }
}