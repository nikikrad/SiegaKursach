package com.example.siegakursach.view.game.match.tabslayout.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.siegakursach.databinding.FragmentCoefficientBinding
import com.example.siegakursach.single.GameData
import com.example.siegakursach.single.GameId
import com.example.siegakursach.view.game.match.tabslayout.coefficient.CoefficientViewModel
import org.koin.android.ext.android.inject

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentCoefficientBinding
    private val coefficientViewModel: CoefficientViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoefficientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        coefficientViewModel.getMatch(GameId.gameId)

//        responseBody.clear()
        coefficientViewModel.liveData.observe(viewLifecycleOwner) { match ->
            try {
                binding.tvHomeOdd.text = match.odds.Bet365.prematch[0].home_od
                binding.tvDrawOdd.text = match.odds.Bet365.prematch[0].draw_od
                binding.tvAwayOdd.text = match.odds.Bet365.prematch[0].away_od
            } catch (e: Exception) {
                binding.tvDrawOdd.text = "Данные отсутствуют"
            }


        }
    }

}