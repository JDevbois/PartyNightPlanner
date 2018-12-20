package com.example.joren.partynightplanner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.joren.partynightplanner.util.DummyData
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

        fabSearch.setOnClickListener {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content, ContentSearch.newInstance())
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun onStart(){
        Log.i("MainActivity", "starting")
        super.onStart()
        layoutManager = LinearLayoutManager(this)
        eventRecycleView.layoutManager = layoutManager

        adapter = EventAdapter(DummyData.getEvents(), this)
        eventRecycleView.adapter = adapter
    }

    override fun onResume(){
        super.onResume()
        Log.i("MainActivity", "resuming")
        layoutManager = LinearLayoutManager(this)
        eventRecycleView.layoutManager = layoutManager

        adapter = EventAdapter(DummyData.getEvents(), this)
        eventRecycleView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "stopping")
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
}
