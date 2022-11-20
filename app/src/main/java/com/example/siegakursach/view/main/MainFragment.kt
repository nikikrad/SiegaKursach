package com.example.siegakursach.view.main

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siegakursach.R
import com.example.siegakursach.databinding.FragmentMainBinding
import com.example.siegakursach.domain.models.byday.GamesDay
import com.example.siegakursach.single.Day
import com.example.siegakursach.single.SportType
import org.koin.android.ext.android.inject
import java.util.*

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        SportType.fullySportList()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val sports = resources.getStringArray(R.array.sport_type)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, sports)

        binding.spinnerSports.adapter = arrayAdapter

        binding.spinnerSports.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                SportType.sportId = position.toString()
                mainViewModel.getGameEvents(SportType.getSport(), "today")
                binding.tvDate.text = "Сегодня"
                adapter()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.tvDate.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Day.day = "${year}${month + 1}${dayOfMonth}"
                binding.tvDate.text = "${dayOfMonth}.${month + 1}"
                mainViewModel.getGameEvents(SportType.getSport(), Day.day)
                binding.rvEvents.adapter = null
                adapter()
            }, year, month, day)
            dpd.show()
        }


    }

    fun adapter() {
        mainViewModel.liveData.observe(viewLifecycleOwner) { games ->

            var shlyapa = games.games_pre.groupBy {
                it.league.name
            }

            val adapter = MainAdapter(shlyapa)
            binding.rvEvents.layoutManager =
                LinearLayoutManager(
                    activity?.applicationContext, LinearLayoutManager.VERTICAL, false
                )
            binding.rvEvents.adapter = adapter

        }
    }

}