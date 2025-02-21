package com.example.movie.presentation.adapter.cast_crew_detail_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie.presentation.view.fragment.tabs_detail_actor.CastCrewFragment

class CastCrewPagerAdapter(activity: FragmentActivity, private val actorId: Int) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2 // 0 = Cast, 1 = Crew

    override fun createFragment(position: Int): Fragment {
        val fragment = CastCrewFragment()
        val bundle = Bundle()
        bundle.putInt("ACTOR_ID", actorId)
        bundle.putString("TYPE", if (position == 0) "cast" else "crew")
        fragment.arguments = bundle
        return fragment
    }
}