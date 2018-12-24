package com.example.joren.partynightplanner.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.R
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.logged_in_fragment.*
import java.util.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.example.joren.partynightplanner.MainActivity
import com.facebook.*
import org.json.JSONObject
import java.net.URL
import java.util.logging.Logger
import im.getsocial.sdk.ui.GetSocialUi




class LoggedInFragment : Fragment() {
    private val EMAIL = "email"
    private val PUBLIC_PROFILE = "public_profile"
    private var callbackManager: CallbackManager = CallbackManager.Factory.create()
    var accessToken: AccessToken? = AccessToken.getCurrentAccessToken()
    var isLoggedIn = accessToken != null && !accessToken!!.isExpired

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.logged_in_fragment, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        btnLogin.setReadPermissions(Arrays.asList(EMAIL, PUBLIC_PROFILE))
        btnLogin.fragment = this

        // Callback registration
        btnLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // App code
                val request = GraphRequest.newMeRequest(loginResult.accessToken) { `object`, response ->
                    try {
                        //here is the data that you want
                        Log.d("FBLOGIN_JSON_RES", `object`.toString())

                        if (`object`.has("id")) {
                            (activity as MainActivity).handleSignInResultFacebook(`object`)
                        } else {
                            Log.d("FBLOGIN_FAILED", `object`.toString())
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                val parameters = Bundle()
                parameters.putString("fields", "name,email,id")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
                Log.i("facebook", exception.message)
            }
        })

        // ONCLICK for plannednights label
        txtPlannedNights.setOnClickListener {
            //val wasShown = GetSocialUi.createInvitesView().show()
            //Log.i("GetSocial", "GetSocial Smart Invites UI was shown: $wasShown")
            (activity as MainActivity).openPlannedNightsPanel()
        }
    }

    companion object {
        fun newInstance(): LoggedInFragment {
            return LoggedInFragment()
        }
    }
}