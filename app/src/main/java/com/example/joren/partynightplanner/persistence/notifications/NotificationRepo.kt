package com.example.joren.partynightplanner.persistence.notifications

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.domain.Notification

class NotificationRepo private constructor(private val notificationDao: NotificationDao) {

    fun getNotifications() = notificationDao.getNotifications()

    fun getNotificationsForUser(id: String): LiveData<List<Notification>> {
        return Transformations.map(getNotifications()){
            it.filter { n -> n.to == id }
        }
    }

    fun deleteNotification(notification: Notification) {
        notificationDao.deleteNotification(notification)
    }

    fun addNotification(userId: String, night: Night, friends: List<String>) {
        friends.filterNot { f -> night.friends.contains(f)}.forEach{
            if (it != userId){
                notificationDao.addNotification(userId, it, night)
            }
        }
    }

    companion object {
        // singleton code
        @Volatile private var instance: NotificationRepo? = null

        fun getInstance(notificationDao: NotificationDao) =
                instance ?: synchronized(this){
                    instance ?: NotificationRepo(notificationDao).also {
                        instance = it
                    }
                }
    }
}