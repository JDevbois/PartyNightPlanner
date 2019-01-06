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
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.adapters.FriendAdapter
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.domain.User
import com.example.joren.partynightplanner.util.InjectorUtils
import com.facebook.GraphRequest
import im.getsocial.sdk.ui.GetSocialUi
import kotlinx.android.synthetic.main.content_invite_friends.*
import org.json.JSONArray
import org.json.JSONObject

class ContentInviteFriends : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<FriendAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_invite_friends, container, false)
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

        arguments!!.let{
            if (it.containsKey(ARG_NIGHT)){
                viewModel.night = it.getSerializable(ARG_NIGHT) as Night
            }
        }

        getUserFriends()

        btnInviteNotOnApp.setOnClickListener {
            val wasShown = GetSocialUi.createInvitesView().show()
            Log.i("GetSocial", "GetSocial Smart Invites UI was shown: $wasShown")
        }

        btnInvite.setOnClickListener {
            viewModel.sendNotificationsForNight((adapter as FriendAdapter).selectedFriends)

            //aTODO: move to when invite is accepted
            viewModel.night.friends.addAll((adapter as FriendAdapter).selectedFriends.filterNot { f -> viewModel.night.friends.contains(f) })
            viewModel.updateNight(viewModel.night)
            fragmentManager!!.popBackStackImmediate()
        }
    }

    fun getUserFriends(){
        if(MainActivity.isLoggedIn){
            val request = GraphRequest.newMeRequest(MainActivity.accessToken) { `object`, response ->
                try {
                    //here is the data that you want
                    Log.d("FBLOGIN_JSON_RES", `object`.toString())

                    if (`object`.has("id")) {
                        handleFacebookFriendsResult(`object`)
                    } else {
                        Log.d("FBLOGIN_FAILED", `object`.toString())
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            val parameters = Bundle()
            parameters.putString("fields", "friends")
            request.parameters = parameters
            request.executeAsync()
        }
    }

    fun handleFacebookFriendsResult(data: JSONObject?){
        val friendsObject = data!!.getJSONObject("friends").getString("data")
        val friends: MutableList<User> = mutableListOf()

        val array = JSONArray(friendsObject)
        for (i in 0 until array.length()) {
            val row = array.getJSONObject(i)
            val id = row.getString("id")
            val name = row.getString("name")
            val user = User(id, name)
            friends.add(user)
        }

        Log.i("FB", friendsObject.toString())
        Log.d("FB", friends.toString())

        layoutManager = LinearLayoutManager(this.context)
        adapter = FriendAdapter(friends, this.activity)

        friendsRecycleView.layoutManager = layoutManager
        friendsRecycleView.adapter = adapter
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