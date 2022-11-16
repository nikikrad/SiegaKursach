package com.example.siegakursach.view.game.match.tabslayout.h2h

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.siegakursach.R
import com.example.siegakursach.domain.models.byday.GamesDay
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2HData
import com.example.siegakursach.view.game.match.tabslayout.table.models.TableRows
import java.time.Instant
import java.time.ZoneId
import java.util.ArrayList
import kotlin.streams.toList

class H2HAdapter(
    private val h2hList: List<H2HData>
) : RecyclerView.Adapter<H2HAdapter.MainViewHolder>() {

    companion object {
        var megastatus = false
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_h2h, parent, false)
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
        private val homeScore: TextView = itemView.findViewById(R.id.tv_ScoreHome)
        private val awayScore: TextView = itemView.findViewById(R.id.tv_ScoreAway)

        private val bundle = Bundle()

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: H2HData) {
            megastatus = false

            homeTeam.text = item.home.name
            awaiTeam.text = item.away.name
            val timestamp = Instant.ofEpochSecond(item.time.toLong())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
            time.text = "${timestamp.dayOfMonth}.${timestamp.monthValue}"
            homeScore.text = item.ss.toCharArray()[0].toString()
            awayScore.text = item.ss.toCharArray()[2].toString()

        }
    }
}