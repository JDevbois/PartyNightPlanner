package com.example.joren.partynightplanner

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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.content, ContentMain.newInstance()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.loggedInFragment, LoggedInFragment()).commit()
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
}
