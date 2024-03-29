package com.example.siegakursach.view.live.match

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.siegakursach.R
import com.example.siegakursach.databinding.FragmentLiveMatchBinding
import com.example.siegakursach.single.GameData
import com.example.siegakursach.single.GameId
import com.example.siegakursach.view.game.match.MatchViewModel
import com.example.siegakursach.view.game.match.adapter.MyFragmentAdapter
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject
import java.time.Instant
import java.time.ZoneId

class LiveMatchFragment: Fragment() {

    lateinit var binding: FragmentLiveMatchBinding
    private val liveMatchViewModel: LiveMatchViewModel by inject()
    private lateinit var myFragmentAdapter: MyFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getInt("STATUS")



        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        liveMatchViewModel.getMatch(GameData.gameId)

//        responseBody.clear()
        liveMatchViewModel.liveData.observe(viewLifecycleOwner) { match ->

//            Log.e("HUH", match.toString())
            binding.tvLeagueName.text = match.results[0].league.name
            binding.tvNameHomeTeam.text = match.results[0].home.name
            binding.tvNameAwayTeam.text = match.results[0].away.name

            val timestamp = Instant.ofEpochSecond(match.results[0].time.toLong())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()

            binding.tvDate.text =
                "${timestamp.dayOfMonth}.${timestamp.monthValue}.${timestamp.year} ${timestamp.hour}:${timestamp.minute}0 "
//            GameId.gameId = match.results[0].id
            try {
                Glide.with(binding.root)
                    .load("https://spoyer.ru/api/team_img/soccer/${match.results[0].home.image_id}.png")
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivHomeTeam)
            } catch (e: Exception) {
            }

            try {
                Glide.with(binding.root)
                    .load("https://spoyer.ru/api/team_img/soccer/${match.results[0].away.image_id}.png")
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivAwayTeam)
            } catch (e: Exception) {
            }

            try {
                binding.tvScore.text = match.results[0].ss

            } catch (e: Exception) {
            }

        }


        myFragmentAdapter = MyFragmentAdapter(childFragmentManager, lifecycle)
        binding.viewPager2.adapter = myFragmentAdapter

        val tabStrip = binding.tabLayout.getChildAt(0) as LinearLayout
        for (i in 0 until tabStrip.childCount) {
            tabStrip.getChildAt(i).setOnLongClickListener {
                true
            }
//            tabStrip.getChildAt(i).isSelected = false
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

    }


}