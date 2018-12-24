package com.example.joren.partynightplanner

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.joren.partynightplanner.adapters.EventAdapter
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.persistence.EventRepo
import com.example.joren.partynightplanner.views.*
import com.facebook.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.facebook.login.LoginManager
import java.util.*
import com.facebook.login.LoginResult
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

        setSupportActionBar(toolbar)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun openDetailPanel(item: Event) {
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
