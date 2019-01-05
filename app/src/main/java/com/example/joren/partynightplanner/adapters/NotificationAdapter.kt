package com.example.joren.partynightplanner.adapters

import android.app.Activity
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.domain.Notification
import com.example.joren.partynightplanner.util.DownloadImageTask
import com.example.joren.partynightplanner.views.notifications.NotificationsViewModel
import com.facebook.login.widget.ProfilePictureView
import java.text.SimpleDateFormat
import java.util.*

class NotificationAdapter(private val dataSet: List<Notification>, private val parentActivity: FragmentActivity?, private val viewModel: NotificationsViewModel): RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context)
                .inflate(R.layout.notification_fragment, parent, false)

        return ViewHolder(view)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = dataSet[position]

        with(holder){
            itemName.text = notification.from
            itemDate.text = SimpleDateFormat("MM/dd/yy", Locale.US).format(notification.date)
            itemPic.profileId = notification.from
            itemMessage.text = notification.message
            itemDeleteButton.setOnClickListener {
                viewModel.deleteNotifcation(notification)
            }
        }
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemName: TextView = itemView.findViewById(R.id.notificationName)
        var itemDate: TextView = itemView.findViewById(R.id.notificationDate)
        var itemPic: ProfilePictureView = itemView.findViewById(R.id.notificationImg)
        var itemMessage: TextView = itemView.findViewById(R.id.notificationMessage)
        var itemDeleteButton: ImageButton = itemView.findViewById(R.id.btnNotificationDelete)
    }
}