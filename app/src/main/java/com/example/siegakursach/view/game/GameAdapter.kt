package com.example.siegakursach.view.game

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.ghurskykursach.presentation.main.MainAdapter
import com.example.siegakursach.R
import com.example.siegakursach.domain.models.byday.GamesDay
import java.sql.Date
import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneId
import java.util.ArrayList
import kotlin.streams.toList

class GameAdapter(
    private val movieList: List<GamesDay>
) : RecyclerView.Adapter<GameAdapter.MainViewHolder>() {

    companion object {
        var megastatus = false
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_game, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val homeTeam: TextView = itemView.findViewById(R.id.tv_HomeTeam)
        private val awaiTeam: TextView = itemView.findViewById(R.id.tv_AwaiTeam)
        private val time: TextView = itemView.findViewById(R.id.tv_Time)

        private val bundle = Bundle()
        fun bind(item: GamesDay) {
            megastatus = false

            try {

                homeTeam.text = item.home.name
                awaiTeam.text = item.away.name
                val timestamp = Instant.ofEpochSecond(item.time.toLong())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
//                val timestamp = Timestamp(item.time.toLong())
//                val date = Date(timestamp.time)
                time.text = timestamp.toString()

            } catch (e: Exception) {
                Log.e("TAG", e.localizedMessage.toString())
            }
//            var gg = item
            itemView.setOnClickListener {
                if (megastatus == false) {
                    megastatus = true
                    bundle.putParcelableArrayList("GAMES", item as ArrayList<out Parcelable>)
                    Navigation.findNavController(itemView)
                        .navigate(R.id.action_mainFragment_to_gameFragment, bundle)
                }
            }

        }
    }
}