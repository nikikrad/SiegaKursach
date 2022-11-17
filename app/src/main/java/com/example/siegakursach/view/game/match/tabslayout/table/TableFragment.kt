package com.example.siegakursach.view.game.match.tabslayout.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siegakursach.databinding.FragmentTableBinding
import com.example.siegakursach.single.GameData
import com.example.siegakursach.single.GameId
import org.koin.android.ext.android.inject

class TableFragment : Fragment() {

    private lateinit var binding: FragmentTableBinding
    private val tableViewModel: TableViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tableViewModel.getMatch(GameId.leagueId)

        binding.tvTable.isVisible = false
        tableViewModel.liveData.observe(viewLifecycleOwner) { match ->
//            Log.e("HUH", match.results.toString())

            try {
                TableAdapter.i = 1
                val adapter = TableAdapter(match.results.overall.tables[0].rows)
                binding.rvTable.layoutManager =
                    LinearLayoutManager(
                        activity?.applicationContext, LinearLayoutManager.VERTICAL, false
                    )
                binding.rvTable.adapter = adapter
            } catch (e: Exception) {
                binding.tvTable.isVisible = true
            }

        }
    }

}