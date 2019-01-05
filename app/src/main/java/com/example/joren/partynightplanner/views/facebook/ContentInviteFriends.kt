package com.example.joren.partynightplanner.views.facebook

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.adapters.FriendAdapter
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.users.UserRepo
import com.example.joren.partynightplanner.util.InjectorUtils
import com.example.joren.partynightplanner.views.newNight.NightViewModel
import im.getsocial.sdk.ui.GetSocialUi
import kotlinx.android.synthetic.main.content_invite_friends.*

//TODO: MVVM
class ContentInviteFriends : Fragment() {

    lateinit var night: Night

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<FriendAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_invite_friends, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO graph request to fb api for user_friends

        arguments!!.let{
            if (it.containsKey(ARG_NIGHT)){
                night = it.getSerializable(ARG_NIGHT) as Night
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initUi()
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

    private fun initUi(){
        val factory = InjectorUtils.provideInviteFriendsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(InviteFriendsViewModel::class.java)
        layoutManager = LinearLayoutManager(this.context)
        //TODO: facebook user_friends whose IDS are NOT in night already
        adapter = FriendAdapter(UserRepo.users.map { u -> u.id }, this.activity)

        friendsRecycleView.layoutManager = layoutManager
        friendsRecycleView.adapter = adapter

        btnInviteNotOnApp.setOnClickListener {
            val wasShown = GetSocialUi.createInvitesView().show()
            Log.i("GetSocial", "GetSocial Smart Invites UI was shown: $wasShown")
        }

        btnInvite.setOnClickListener {
            viewModel.sendNotificationsForNight(night, (adapter as FriendAdapter).selectedFriends)
            
            //TODO: move to when invite is accepted
            night.friends.addAll((adapter as FriendAdapter).selectedFriends.filterNot { f -> night.friends.contains(f) })
            viewModel.updateNight(night)
            fragmentManager!!.popBackStackImmediate()
        }
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