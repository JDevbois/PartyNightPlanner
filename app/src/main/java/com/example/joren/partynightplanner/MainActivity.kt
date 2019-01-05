package com.example.joren.partynightplanner

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.users.UserRepo
import com.example.joren.partynightplanner.util.InjectorUtils
import com.example.joren.partynightplanner.views.*
import com.example.joren.partynightplanner.views.details.ContentNightDetail
import com.example.joren.partynightplanner.views.details.EventDetailFragment
import com.example.joren.partynightplanner.views.facebook.ContentInviteFriends
import com.example.joren.partynightplanner.views.facebook.LoggedInFragment
import com.example.joren.partynightplanner.views.newNight.ContentAddEventToNight
import com.example.joren.partynightplanner.views.newNight.ContentNewNight
import com.example.joren.partynightplanner.views.notifications.ContentNotifications
import com.example.joren.partynightplanner.views.plannedNights.ContentPlannedNights
import com.example.joren.partynightplanner.views.search.ContentSearch
import com.example.joren.partynightplanner.views.search.ContentSearchResult
import com.facebook.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.logged_in_fragment.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager = CallbackManager.Factory.create()
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        UserRepo.getUserFriends()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if(drawer_layout.isDrawerOpen(GravityCompat.START))
                    drawer_layout.closeDrawers()
                else
                    drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun openEventDetailPanel(item: Event) {
        val fragment = EventDetailFragment.newInstance(item)
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun openSearchResultPanel(option: Int, query: String){
        val fragment = ContentSearchResult.newInstance(option, query)
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun openSearchPanel() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, ContentSearch.newInstance())
                .addToBackStack(null)
                .commit()
    }

    fun openPlannedNightsPanel() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, ContentPlannedNights.newInstance())
                .addToBackStack(null)
                .commit()
    }

    fun openNightDetailPanel(item: Night) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, ContentNightDetail.newInstance(item))
                .addToBackStack(null)
                .commit()
    }

    fun openNewNightPanel() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, ContentNewNight.newInstance())
                .addToBackStack(null)
                .commit()
    }

    private fun loadFbData(){
        //LOAD fb data
        if(isLoggedIn){
            val request = GraphRequest.newMeRequest(accessToken) { `object`, response ->
                try {
                    //here is the data that you want
                    Log.d("FBLOGIN_JSON_RES", `object`.toString())

                    if (`object`.has("id")) {
                        handleSignInResultFacebook(`object`)
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
    }

    fun handleSignInResultFacebook(data: JSONObject?) {
        imgProfile.profileId = data!!["id"].toString()
        currUserId = data["id"].toString()
    }

    fun saveNight(night: Night) {
        viewModel.addNight(night)
        openPlannedNightsPanel()
    }

    fun openAddEventToNightPanel(night: Night) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, ContentAddEventToNight.newInstance(night))
                .addToBackStack(null)
                .commit()
    }

    fun addEventToNight(selectedItem: Event?, night: Night) {
        if (selectedItem != null) {
            night.events.add(selectedItem)
            openNewNightPanel(night)
        } else {
            Toast.makeText(this, "No event was added because you did not select one", Toast.LENGTH_LONG).show()
            openNewNightPanel()
        }
    }

    fun openInviteFriendsPanel(item: Night) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, ContentInviteFriends.newInstance(item))
                .addToBackStack(null)
                .commit()
    }

    private fun openNewNightPanel(night: Night) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, ContentNewNight.newInstance(night))
                .addToBackStack(null)
                .commit()
    }

    private fun initUi(){
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.loggedInFragment, LoggedInFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.content, ContentMain.newInstance()).commit()

        loadFbData()

        val factory = InjectorUtils.provideMainViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }

        nav_view.setNavigationItemSelectedListener {

            it.isChecked = true
            drawer_layout.closeDrawers()

            // move to viewmodel?
            when (it.itemId){
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.content, ContentMain.newInstance()).commit()
                    true
                }
                R.id.nav_nights -> {
                    openPlannedNightsPanel()
                    true
                }
                R.id.nav_search -> {
                    openSearchPanel()
                    true
                }
                R.id.nav_invitations -> {
                    openNotificationsPanel()
                    true
                }
                else -> false
            }
        }
    }

    private fun openNotificationsPanel() {
        supportFragmentManager.beginTransaction().replace(R.id.content, ContentNotifications.newInstance()).commit()
    }

    companion object {
        // TODO: shield routes with isLoggedIn
        var accessToken: AccessToken? = AccessToken.getCurrentAccessToken()
        var isLoggedIn = accessToken != null && ! accessToken!!.isExpired
        var currUserId: String = ""
    }
}
