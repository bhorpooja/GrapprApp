package com.codekul.grapprapp

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        setRecyclerList()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)



    }

     fun setRecyclerList() {
         val _recyclerView: RecyclerView = findViewById(R.id.recyclerView)
         _recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

         val items = ArrayList<MyDataClass>()

         items.add(MyDataClass(R.drawable.ic_alarm_clock,"AlarmClock","wednesday"))
         items.add(MyDataClass(R.drawable.ic_gmail,"Gmail","bhorpooja22@gmail.com"))
         items.add(MyDataClass(R.drawable.ic_facebook,"Facebook","bhorpooja895@gmail.com"))
         items.add(MyDataClass(R.drawable.ic_gallery_,"Gallery","It's My Gallery"))
         items.add(MyDataClass(R.drawable.ic_music_player,"MusicPlayer","Play Music Here"))
         items.add(MyDataClass(R.drawable.ic_playstore,"PlayStore","Get App from Here"))
         items.add(MyDataClass(R.drawable.ic_settings,"Setting","Your Phone Setting"))
         items.add(MyDataClass(R.drawable.ic_whatsapp,"WhatsApp","Hey WhatsApp"))
         items.add(MyDataClass(R.drawable.ic_youtube,"YouTube","Me YouTube"))

         val adapter = MyAdapter(items)

         //now adding the adapter to recyclerview
         _recyclerView.adapter = adapter

     }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_refresh -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_referrals -> {

            }
            R.id.nav_notification -> {

            }
            R.id.nav_wallet -> {

            }
            R.id.nav_faqs -> {

            }
            R.id.nav_aboutUs -> {

            }
            R.id.nav_emailUs -> {

            }
            R.id.nav_share -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
