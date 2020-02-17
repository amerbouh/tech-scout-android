package com.team3990.techscouting.ui.search

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.team3990.techscouting.R
import kotlinx.android.synthetic.main.activity_search.*

private const val NUM_PAGES = 2
private val PAGE_TITLES_RESOURCE_IDENTIFIERS = arrayOf(R.string.match_scouting, R.string.pit_scouting)

class SearchActivity : AppCompatActivity() {

    /** Activity's lifecycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Setup the action bar.
        setupActionBar()

        // Initialize the pager's adapter instance.
        pager.adapter = DataSheetSliderPagerAdapter(supportFragmentManager)

        // Setup the tab layout with the view pager.
        tabLayout.setupWithViewPager(pager)

        // Receive the query.
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                supportActionBar?.title = query
            }
        }
    }

    /** Methods */

    override fun onBackPressed() {
        if (pager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            pager.currentItem = pager.currentItem - 1
        }
    }

    private fun setupActionBar() {
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * A simple pager adapter that represents two ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class DataSheetSliderPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment = Fragment()

        override fun getPageTitle(position: Int): CharSequence? = resources.getString(
            PAGE_TITLES_RESOURCE_IDENTIFIERS[position]
        )

    }

}
