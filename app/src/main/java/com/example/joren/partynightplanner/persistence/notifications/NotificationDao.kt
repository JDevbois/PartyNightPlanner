package com.example.joren.partynightplanner.persistence.notifications

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.domain.Notification
import com.example.joren.partynightplanner.persistence.Database
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class NotificationDao {
    private val notificationList = mutableListOf<Notification>()
    private val notifications = MutableLiveData<List<Notification>>()

    fun getNotifications() = notifications as LiveData<List<Notification>>

    init {
        val notificationListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                refreshNotifications(p0)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.i("Firebase","loadPost:onCancelled ${p0.toException()}")
            }

        }

        Database.notificationCloudEndPoint.addValueEventListener(notificationListener)
        notifications.value = notificationList
    }

    fun refreshNotifications(p0: DataSnapshot) {
        notificationList.clear()
        p0.children.mapNotNullTo(notificationList){
            it.getValue<Notification>(Notification::class.java)
        }
        notifications.value = notificationList

        // debugging purposes only, remove for release
        Log.i("DB", notificationList.joinToString(separator = ", ") { n -> n.toString() })
    }

    private fun getNotificationMessage(flag: Int, tag: String): String{
        return when (flag){
            NIGHTINVITE -> {
                "has invited you to $tag"
            }
            else -> ""
        }
    }

    fun deleteNotification(notification: Notification) {
        val key = notification.id
        if (key.isNotEmpty()){
            Database.notificationCloudEndPoint.child(key).removeValue()
            Log.d("notification", Database.notificationCloudEndPoint.child(key).toString())
        }
    }

    fun addNotification(id: String, friend: String, night: Night) {
        val key = Database.notificationCloudEndPoint.push().key
        if (key != null){
            val n = Notification(key, id, friend, getNotificationMessage(NIGHTINVITE, night.name), false, Calendar.getInstance().time)
            Database.notificationCloudEndPoint.child(key).setValue(n)
        }
    }

    private companion object {
        const val NIGHTINVITE = 1
    }
}