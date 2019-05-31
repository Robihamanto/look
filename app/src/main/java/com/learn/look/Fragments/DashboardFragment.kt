package com.learn.look.Fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.learn.look.R
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureTabLayout()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    fun configureTabLayout() {
        pager.adapter = TabPagerAdapter(activity!!.supportFragmentManager)
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }


    inner class TabPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when(position) {
                0 -> {
                    return PopularMovieFragment()
                }
                1 -> {
                    return TopRatedMovieFragment()
                }
                else -> return null
            }
        }

        override fun getCount(): Int {
            return 2
        }

    }


}
