package com.example.siegakursach.view.game

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siegakursach.databinding.FragmentGameBinding
import com.example.siegakursach.domain.models.byday.GamesDay
import com.example.siegakursach.single.TaskType
import com.example.siegakursach.view.game.model.GamesEnd

class GameFragment: Fragment() {

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    lateinit var gameList: List<GamesDay>
    lateinit var endGameList: List<GamesEnd>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (TaskType.task == "predata"){
            gameList = arguments?.getParcelableArrayList<Parcelable>("GAMES") as List<GamesDay>

            binding.tvLeagueName.text = gameList[0].league.name

            val adapter = GameAdapter(gameList)
            binding.rvGame.layoutManager =
                LinearLayoutManager(
                    activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
            binding.rvGame.adapter = adapter
        }else{
            endGameList = arguments?.getParcelableArrayList<Parcelable>("GAMES") as List<GamesEnd>

            try{
                binding.tvLeagueName.text = endGameList[0].league.name
            }catch (e: Exception){}

            val adapter = EndGamesAdapter(endGameList)
            binding.rvGame.layoutManager =
                LinearLayoutManager(
                    activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
            binding.rvGame.adapter = adapter
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}