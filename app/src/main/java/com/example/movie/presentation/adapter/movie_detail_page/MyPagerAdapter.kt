package com.example.movie.presentation.adapter.movie_detail_page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie.presentation.view.fragment.tabs_detail_movie.AboutFragment
import com.example.movie.presentation.view.fragment.tabs_detail_movie.EpisodeFragment
import com.example.movie.presentation.view.fragment.tabs_detail_movie.ReviewFragment
import com.example.movie.presentation.view.fragment.tabs_detail_movie.SimilarFragment

class MyPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {

        return 4
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> EpisodeFragment()
            1 -> SimilarFragment()
            2 -> AboutFragment()
            3 -> ReviewFragment()
            else -> throw IllegalArgumentException("Invalid tab position: $position")
        }
    }
}

