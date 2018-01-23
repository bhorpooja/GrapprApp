package com.codekul.grapprapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.codekul.grapprapp.fragment.*
import com.codekul.grapprapp.sharedprefs.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var fragment:Fragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fragment=HomeFragment()
        val ft=supportFragmentManager.beginTransaction()
        ft.replace(R.id.contentFrame,fragment)
        ft.commit()

        val txtNumber=findViewById<TextView>(R.id.txtNumber)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

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
        val mobileNumber=Prefs.getMobileNo(this)
        Log.i("@codekul","mobileNo: "+mobileNumber)
        txtNumber.text=Prefs.getMobileNo(applicationContext)
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
        displaySelectedScreen(item.itemId)
        return true
    }
        private fun displaySelectedScreen(itemId:Int){



            when (itemId) {
                R.id.nav_home->fragment=HomeFragment()
                R.id.nav_referrals->fragment=ReferalFragment()
                R.id.nav_notification->fragment=NotificationFragment()
                R.id.nav_faqs->fragment=FaqsFragment()
                R.id.nav_aboutUs->fragment=AboutusFragment()
                R.id.nav_logout->{
                    Prefs.clearUserData(applicationContext)
                    startActivity(Intent(applicationContext,LoginActivity::class.java))
                }
            }

            if (fragment!=null){
                val ft=supportFragmentManager.beginTransaction()
                ft.replace(R.id.contentFrame,fragment)
                ft.addToBackStack(null)
                ft.commit()
            }

            val drawer=findViewById<View>(R.id.drawer_layout) as DrawerLayout
            drawer.closeDrawer(GravityCompat.START)
        }

}
