package com.example.joren.partynightplanner.views.newNight

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.events.EventRepo
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.HttpMethod

class NightViewModel(private val eventRepo: EventRepo): ViewModel() {
    var night: Night = Night().also {
        if (MainActivity.isLoggedIn){
            it.friends.add(MainActivity.currUserId)
        }
    }

    private val friendNamesList: MutableList<String> = mutableListOf()
    private val friendNames = MutableLiveData<MutableList<String>>()

    fun parseNight(p0: Night){
        night = p0
        night.friends.forEach { f -> addFriendName(f) }
        friendNames.value = friendNamesList
    }

    fun addFriendName(s: String){
        GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/$s/",
                null,
                HttpMethod.GET,
                GraphRequest.Callback {
                    handleResult(it)
                }
        ).executeAsync()
    }

    private fun handleResult(it: GraphResponse) {
        Log.d("FB", it.jsonObject.toString())
        val name: String = it.jsonObject.getString("name").substring(0, it.jsonObject.getString("name").indexOf(' '))
        friendNamesList.add(name)
        friendNames.value = friendNamesList
    }

    fun getFriendNames() = friendNames as LiveData<MutableList<String>>

    fun getAvailableEvents() = eventRepo.getAddableEventsForNight(night)

    fun getEvents() = night.events
}