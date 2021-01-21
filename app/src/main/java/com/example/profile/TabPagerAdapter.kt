package com.example.profile

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.profile.Fragment.LikesFragment
import com.example.profile.Fragment.MyDesignsFragment
import com.example.profile.Fragment.PurchasedFragment

class TabPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LikesFragment()
            1 -> MyDesignsFragment()
            else -> PurchasedFragment()
        }
    }
}