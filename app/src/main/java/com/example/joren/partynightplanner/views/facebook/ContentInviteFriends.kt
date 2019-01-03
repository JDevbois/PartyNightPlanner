package com.example.joren.partynightplanner.views.facebook

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.adapters.EventAdapter
import com.example.joren.partynightplanner.adapters.FriendAdapter
import com.example.joren.partynightplanner.adapters.SelectEventAdapter
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.events.EventRepo
import com.example.joren.partynightplanner.util.DummyData
import com.example.joren.partynightplanner.views.newNight.ContentAddEventToNight
import im.getsocial.sdk.ui.GetSocialUi
import kotlinx.android.synthetic.main.content_add_event_to_night.*
import kotlinx.android.synthetic.main.content_invite_friends.*

class ContentInviteFriends : Fragment() {

    lateinit var night: Night

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<FriendAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_invite_friends, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO graph request to fb api for invite_friends, if not empty -> hide fb regulations textview

        arguments!!.let{
            if (it.containsKey(ARG_NIGHT)){
                night = it.getSerializable(ARG_NIGHT) as Night
            }
        }
    }

    override fun onStart() {
        super.onStart()
        btnInviteNotOnApp.setOnClickListener {
            val wasShown = GetSocialUi.createInvitesView().show()
            Log.i("GetSocial", "GetSocial Smart Invites UI was shown: $wasShown")
        }

        btnInvite.setOnClickListener {
            //TODO add selected userids to night
        }

        layoutManager = LinearLayoutManager(this.context)
        adapter = FriendAdapter(night.friends, this.activity)

        friendsRecycleView.layoutManager = layoutManager
        friendsRecycleView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        btnInviteNotOnApp.setOnClickListener(null)
        btnInvite.setOnClickListener(null)

        layoutManager = null
        adapter = null

        friendsRecycleView.layoutManager = null
        friendsRecycleView.adapter = null
    }

    companion object {
        fun newInstance(night: Night): ContentInviteFriends{
            val fragment = ContentInviteFriends()
            val args = Bundle()
            args.putSerializable(ARG_NIGHT, night)
            fragment.arguments = args
            return fragment
        }

        const val ARG_NIGHT = "item_id"
    }
}