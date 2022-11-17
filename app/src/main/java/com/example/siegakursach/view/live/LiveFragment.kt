package com.example.siegakursach.view.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siegakursach.databinding.FragmentLiveBinding
import org.koin.android.ext.android.inject

class LiveFragment: Fragment() {

    lateinit var binding: FragmentLiveBinding
    private val liveViewModel: LiveViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        liveViewModel.getMatch()
//        responseBody.clear()
        liveViewModel.liveData.observe(viewLifecycleOwner) { match ->
//            Log.e("HUH", match.results.h2h.toString() )

            val adapter = LiveAdapter(match.games_live)
            binding.rvLive.layoutManager =
                LinearLayoutManager(
                    activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
            binding.rvLive.adapter = adapter
        }
    }

}