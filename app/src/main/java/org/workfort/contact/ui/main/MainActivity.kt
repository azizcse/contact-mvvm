package org.workfort.contact.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.ViewGroup
import org.workfort.contact.R
import org.workfort.contact.ui.custom.FragmentPagerItem
import org.workfort.contact.ui.custom.FragmentPagerItemAdapter
import org.workfort.contact.ui.custom.FragmentPagerItems
import org.workfort.contact.ui.custom.SmartTabLayout
import org.workfort.contact.util.ScreenState

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tab = findViewById(R.id.tab) as ViewGroup
        tab.addView(LayoutInflater.from(this).inflate(R.layout.demo_indicator_trick1, tab, false))
        val viewPager = findViewById(R.id.viewpager) as ViewPager
        val viewPagerTab = findViewById(R.id.viewpagertab) as SmartTabLayout

        val pages = FragmentPagerItems(this)
        pages.add(FragmentPagerItem.of("One", DemoFragment::class.java))
        pages.add(FragmentPagerItem.of("Two", DemoFragment::class.java))
        pages.add(FragmentPagerItem.of("Three", DemoFragment::class.java))
        pages.add(FragmentPagerItem.of("Four", DemoFragment::class.java))

        val adapter = FragmentPagerItemAdapter(supportFragmentManager, pages)

        viewPager.adapter = adapter
        viewPagerTab.setViewPager(viewPager)

    }

    private fun showValue(screenState: ScreenState) = when (screenState) {
        is ScreenState.ERROR -> print("error")

        is ScreenState.LOADING->print("Loading")

        is ScreenState.DataValue ->{
            screenState.name
        }
    }
}
