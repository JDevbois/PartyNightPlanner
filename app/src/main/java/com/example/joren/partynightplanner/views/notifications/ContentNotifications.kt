package com.example.joren.partynightplanner.views.notifications

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.adapters.NotificationAdapter
import com.example.joren.partynightplanner.util.InjectorUtils
import kotlinx.android.synthetic.main.content_notifications.*

class ContentNotifications: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NotificationAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_notifications, container, false)

    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    override fun onStop() {
        super.onStop()

        layoutManager = null
        adapter = null
        notificationsRecyclerView.adapter = null
        layoutManager = null
    }

    private fun initUi(){
        val factory = InjectorUtils.provideNotificationsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(NotificationsViewModel::class.java)

        viewModel.getNotificationsForUser().observe(this, Observer {
            adapter = NotificationAdapter(it!!, activity, viewModel)
            notificationsRecyclerView.adapter = adapter
        })

        layoutManager = LinearLayoutManager(context)
        notificationsRecyclerView.layoutManager = layoutManager
    }

    companion object {
        fun newInstance(): ContentNotifications{
            return ContentNotifications()
        }
    }
}
