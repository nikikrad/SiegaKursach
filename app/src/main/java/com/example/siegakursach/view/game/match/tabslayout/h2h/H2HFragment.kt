package com.example.siegakursach.view.game.match.tabslayout.h2h

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siegakursach.databinding.FragmentH2hBinding
import com.example.siegakursach.single.GameData
import com.example.siegakursach.single.GameId
import com.example.siegakursach.single.Status
import org.koin.android.ext.android.inject

class H2HFragment : Fragment() {

    lateinit var binding: FragmentH2hBinding
    private val h2HViewModel: H2HViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentH2hBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(Status.status.toInt() == 0){
            h2HViewModel.getMatch(GameId.gameId)
        }else
            h2HViewModel.getMatch(GameData.gameId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


//        responseBody.clear()
        h2HViewModel.liveData.observe(viewLifecycleOwner) { match ->
            Log.e("HUH", match.results.h2h.toString())

            try{
                val adapter = H2HAdapter(match.results.home)
                binding.rvH2H.layoutManager =
                    LinearLayoutManager(
                        activity?.applicationContext, LinearLayoutManager.VERTICAL, false
                    )
                binding.rvH2H.adapter = adapter
            }catch (e: Exception){

            }

        }
    }
}