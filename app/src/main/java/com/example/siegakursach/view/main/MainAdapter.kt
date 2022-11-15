package com.example.ghurskykursach.presentation.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.siegakursach.R
import com.example.siegakursach.domain.models.byday.GamesDay
import kotlin.streams.toList

class MainAdapter(
    private val movieList: Map<String, List<GamesDay>>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    companion object {
        var megastatus = false
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(
            (movieList.keys.stream()).toList()[position],
            (movieList.values.stream().toList()[position])
        )
    }

    override fun getItemCount(): Int = movieList.size

    class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val leagueName: TextView = itemView.findViewById(R.id.tv_LeagueName)
        private val number: TextView = itemView.findViewById((R.id.tv_Number))

        private val bundle = Bundle()
        fun bind(league: String, item: List<GamesDay>) {
            megastatus = false

            try {
                leagueName.text = league.toString()
                number.text = item.size.toString()

            } catch (e: Exception) {
                Log.e("TAG", e.localizedMessage.toString())
            }

//
//            itemView.setOnClickListener {
//                if (megastatus == false) {
//                    megastatus = true
//                    bundle.putInt("ID", item.id)
//                    Navigation.findNavController(itemView)
//                        .navigate(R.id.action_mainFragment_to_movieDescriptionFragment, bundle)
//                }
//            }

        }
    }
}