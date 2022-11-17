package com.example.siegakursach.view.live

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.siegakursach.R
import com.example.siegakursach.view.game.match.tabslayout.h2h.H2HAdapter
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2HData
import com.example.siegakursach.view.live.models.Live
import com.example.siegakursach.view.live.models.LiveGames
import java.time.Instant
import java.time.ZoneId

class LiveAdapter(
    private val h2hList: List<LiveGames>
) : RecyclerView.Adapter<LiveAdapter.MainViewHolder>() {

    companion object {
        var megastatus = false
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_live, parent, false)
        return MainViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(h2hList[position])
    }

    override fun getItemCount(): Int = h2hList.size

    class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val homeTeam: TextView = itemView.findViewById(R.id.tv_HomeTeam)
        private val awaiTeam: TextView = itemView.findViewById(R.id.tv_AwaiTeam)
        private val time: TextView = itemView.findViewById(R.id.tv_Time)
        private val homeScore: TextView = itemView.findViewById(R.id.tv_HomeScore)
        private val awayScore: TextView = itemView.findViewById(R.id.tv_AwayScore)

        private val bundle = Bundle()

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: LiveGames) {
            megastatus = false

            homeTeam.text = item.home.name
            awaiTeam.text = item.away.name

            time.text = item.timer
            try {
                homeScore.text = item.score.toCharArray()[0].toString()
                awayScore.text = item.score.toCharArray()[2].toString()
            }catch (e:Exception){

            }


        }
    }
}