package com.example.joren.partynightplanner.persistence.users

import android.os.Bundle
import android.util.Log
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.domain.User
import com.example.joren.partynightplanner.util.DummyData
import com.facebook.GraphRequest
import org.json.JSONObject

//TODO: refactor to use fb api only
object UserRepo {
    val users = DummyData.users

    //TODO via facebook api
    fun findUserById(id: String): User {
        return DummyData.users.first { u -> u.id == id }
    }

    //TODO
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
        Log.i("FB", data!!["friends"].toString())
    }
}
