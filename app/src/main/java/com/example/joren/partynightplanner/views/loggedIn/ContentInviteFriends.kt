package com.example.joren.partynightplanner.views.loggedIn

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.R
import im.getsocial.sdk.ui.GetSocialUi
import kotlinx.android.synthetic.main.content_invite_friends.*

class ContentInviteFriends : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_invite_friends, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO graph request to fb api for invite_friends, if not empty -> hide fb regulations textview
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
    }

    override fun onStop() {
        super.onStop()
        btnInviteNotOnApp.setOnClickListener(null)
        btnInvite.setOnClickListener(null)
    }

    companion object {
        fun newInstance(): ContentInviteFriends{
            return ContentInviteFriends()
        }
    }
}