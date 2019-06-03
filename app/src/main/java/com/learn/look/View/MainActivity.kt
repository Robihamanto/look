package com.learn.look.View

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toolbar
import com.learn.look.Fragments.DashboardFragment
import com.learn.look.Fragments.HomeFragment
import com.learn.look.Fragments.ProfileFragment
import com.learn.look.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView

    private final val home = HomeFragment()
    private final val dashboard = DashboardFragment()
    private final val profile = ProfileFragment()
    private final val fm = supportFragmentManager
    private var active: Fragment = home

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
        home_toolbar.title.text = "動態影像"
        setupFragment()
    }

    private fun setupFragment() {
        fm.beginTransaction().add(R.id.main_container, profile, "3").hide(profile).commit()
        fm.beginTransaction().add(R.id.main_container, dashboard, "2").hide(dashboard).commit()
        fm.beginTransaction().add(R.id.main_container,home, "1").commit()
    }
}
