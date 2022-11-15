package com.example.siegakursach.view.main

import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghurskykursach.presentation.main.MainAdapter
import com.example.siegakursach.databinding.FragmentMainBinding
import com.example.siegakursach.domain.models.byday.GamesDay
import org.koin.android.ext.android.inject
import java.util.Dictionary

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by inject()
    private var responseBody: MutableList<GamesDay> = emptyList<GamesDay>().toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mainViewModel.getGameEvents("soccer", "today")
        responseBody.clear()
        mainViewModel.liveData.observe(viewLifecycleOwner) { games ->

            var shlyapa = games.games_pre.groupBy{
                it.league.name
            }

            val adapter = MainAdapter(shlyapa)
            binding.rvEvents.layoutManager =
                LinearLayoutManager(
                    activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
            binding.rvEvents.adapter = adapter

            binding.spinnerSports.setOnItemClickListener { parent, view, position, id ->
                Log.e("HUH", id.toString() )
                Log.e("HUH", position.toString() )
                Log.e("HUH", view.transitionName )
//                Log.e("HUH", view.focusable.toString() )
                Log.e("HUH", parent.transitionName )
                Log.e("HUH", parent.selectedItemId.toString() )
                Log.e("HUH", parent.selectedItemPosition.toString() )
            }

        //        { parent, view, position, id ->
//
//
//
//            }
        }
    }

}