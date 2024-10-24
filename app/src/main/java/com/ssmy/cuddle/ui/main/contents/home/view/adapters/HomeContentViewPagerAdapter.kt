package com.ssmy.cuddle.ui.main.contents.home.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssmy.cuddle.ui.main.contents.home.view.fragments.AnimalFragment
import com.ssmy.cuddle.ui.main.contents.home.view.fragments.DailyFragment
import com.ssmy.cuddle.ui.main.contents.home.view.fragments.TravelFragment

/**
 * doc 주석
 * @author wookjin
 * @since 8/8/24
 **/
class HomeContentViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DailyFragment()  // 일상 탭
            1 -> TravelFragment() // 여행기 탭
            2 -> AnimalFragment() // Cuddle 과 함께하는 동물들 탭
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
