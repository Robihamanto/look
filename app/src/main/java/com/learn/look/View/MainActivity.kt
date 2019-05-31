package com.learn.look.View

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.learn.look.Fragments.DashboardFragment
import com.learn.look.Fragments.HomeFragment
import com.learn.look.Fragments.ProfileFragment
import com.learn.look.R

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView

    final val home = HomeFragment()
    final val dashboard = DashboardFragment()
    final val profile = ProfileFragment()
    final val fm = supportFragmentManager
    var active: Fragment = home

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                fm.beginTransaction().hide(active).show(home).commit()
                active = home
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                fm.beginTransaction().hide(active).show(dashboard).commit()
                active = dashboard
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                fm.beginTransaction().hide(active).show(profile).commit()
                active = profile
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        setupFragment()
    }

    fun setupFragment() {
        fm.beginTransaction().add(R.id.main_container, profile, "3").hide(profile).commit()
        fm.beginTransaction().add(R.id.main_container, dashboard, "2").hide(dashboard).commit()
        fm.beginTransaction().add(R.id.main_container,home, "1").commit()
    }
}
