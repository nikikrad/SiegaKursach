package com.example.siegakursach.view.game.match.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.siegakursach.view.game.match.tabslayout.h2h.H2HFragment
import com.example.siegakursach.view.game.match.tabslayout.info.InfoFragment
import com.example.siegakursach.view.game.match.tabslayout.kaf.CoefficientFragment
import com.example.siegakursach.view.game.match.tabslayout.table.TableFragment


class MyFragmentAdapter(
    fragmentManager: FragmentManager?,
    lifecycle: Lifecycle?
) : FragmentStateAdapter(fragmentManager!!, lifecycle!!) {

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return InfoFragment()
        } else if (position == 1) {
//            return CoefficientFragment()
            return H2HFragment()
        } else
//            if(position == 2) {
//            return H2HFragment()
            return TableFragment()
//        }else
//            return TableFragment()
//    }

    }

    override fun getItemCount(): Int {
        return 3
    }
}