package com.example.siegakursach.view.game.match

import android.os.Build
import android.os.Bundle
import android.util.Log
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
import com.example.siegakursach.databinding.FragmentMatchBinding
import com.example.siegakursach.single.GameData
import org.koin.android.ext.android.inject
import com.example.siegakursach.single.GameId
import com.example.siegakursach.single.SportType
import com.example.siegakursach.view.favorite.auth.model.GameRequest
import com.example.siegakursach.view.game.match.adapter.MyFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.time.ZoneId


class MatchFragment : Fragment() {

    private lateinit var binding: FragmentMatchBinding
    private val matchViewModel: MatchViewModel by inject()
    private lateinit var myFragmentAdapter: MyFragmentAdapter
    lateinit var auth: FirebaseAuth
    lateinit var database: DatabaseReference
    var megaStatus = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getInt("STATUS")

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference



        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        matchViewModel.getMatch(GameId.gameId)

//        responseBody.clear()
        matchViewModel.match.observe(viewLifecycleOwner) { match ->

//            Log.e("HUH", match.toString())
            binding.tvLeagueName.text = match.results[0].league.name
            binding.tvNameHomeTeam.text = match.results[0].home.name
            binding.tvNameAwayTeam.text = match.results[0].away.name

            val timestamp = Instant.ofEpochSecond(match.results[0].time.toLong())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()

            if(timestamp.minute.toString().length == 1){
                binding.tvDate.text =
                    "${timestamp.dayOfMonth}.${timestamp.monthValue}.${timestamp.year} ${timestamp.hour}:${timestamp.minute}0"
            }else
                binding.tvDate.text =
                    "${timestamp.dayOfMonth}.${timestamp.monthValue}.${timestamp.year} ${timestamp.hour}:${timestamp.minute}"


//            GameId.gameId = match.results[0].id
            try {
                Glide.with(binding.root)
                    .load("https://spoyer.ru/api/team_img/${SportType.getSport()}/${match.results[0].home.image_id}.png")
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivHomeTeam)
            } catch (e: Exception) {
            }

            try {
                Glide.with(binding.root)
                    .load("https://spoyer.ru/api/team_img/${SportType.getSport()}/${match.results[0].away.image_id}.png")
                    .placeholder(R.drawable.ic_search)
                    .into(binding.ivAwayTeam)
            } catch (e: Exception) {
            }

            try {
                binding.tvScore.text = match.results[0].ss

            } catch (e: Exception) {
            }


            matchViewModel.processingData(match.results[0].id)

            matchViewModel.status.observe(viewLifecycleOwner) {bool ->
                if (bool){
                    binding.btnFavorite.setImageResource(R.drawable.ic_white_fav)
                }else{
                    binding.btnFavorite.setImageResource(R.drawable.ic_white_unfav)
                }
                binding.btnFavorite.setOnClickListener {
                    if (auth.currentUser != null) {
                        if (!bool) {
                            database.child(
                                auth.currentUser?.email.toString().substringBefore("@")
                            )
                                .child(match.results[0].id).setValue(
                                    GameRequest(
                                        match.results[0].id,
                                        match.results[0].home.name,
                                        match.results[0].away.name,
                                        match.results[0].time
                                    )
                                ).addOnSuccessListener {
                                    binding.btnFavorite.setImageResource(R.drawable.ic_white_fav)
                                }.addOnFailureListener {
                                    Log.e("HUH", it.localizedMessage)
                                }
                        } else {
                            database.child(
                                auth.currentUser?.email.toString().substringBefore("@")
                            ).get()
                                .addOnSuccessListener {
                                    it.child(match.results[0].id).ref.removeValue()
                                        .addOnSuccessListener {
                                            binding.btnFavorite.setImageResource(R.drawable.ic_white_unfav)
                                        }
                                }
                        }

                    } else {
//                        Toast.makeText(context, "Войдите в аккаунт!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }


        myFragmentAdapter = MyFragmentAdapter(childFragmentManager, lifecycle)
        binding.viewPager2.adapter = myFragmentAdapter

        val tabStrip = binding.tabLayout.getChildAt(0) as LinearLayout
        for (i in 0 until tabStrip.childCount) {
            tabStrip.getChildAt(i).setOnLongClickListener {
                true
            }
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