package com.example.joren.partynightplanner

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.joren.partynightplanner.adapters.EventAdapter
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.EventRepo
import com.example.joren.partynightplanner.views.*
import com.facebook.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.logged_in_fragment.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    var accessToken: AccessToken? = AccessToken.getCurrentAccessToken()
    var isLoggedIn = accessToken != null && !accessToken!!.isExpired
    private var callbackManager: CallbackManager = CallbackManager.Factory.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.loggedInFragment, LoggedInFragment.newInstance()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.content, ContentMain.newInstance()).commit()

        loadFbData()

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
                    true
                }
                else -> false
            }
        }

    }

    override fun onStart(){
        Log.i("MainActivity", "starting")
        super.onStart()

        layoutManager = LinearLayoutManager(this)
        eventRecycleView.layoutManager = layoutManager

        adapter = EventAdapter(EventRepo.getAllEvents(), this)
        eventRecycleView.adapter = adapter
    }

    override fun onResume(){
        Log.i("MainActivity", "resuming")

        super.onResume()
        layoutManager = LinearLayoutManager(this)
        eventRecycleView.layoutManager = layoutManager

        adapter = EventAdapter(EventRepo.getAllEvents(), this)
        eventRecycleView.adapter = adapter
    }

    override fun onStop() {
        Log.i("MainActivity", "stopping")
        layoutManager = null
        adapter = null

        super.onStop()
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

    fun loadFbData(){
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
    }
}
