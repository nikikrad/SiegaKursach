package com.example.siegakursach.view.game.match.tabslayout.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siegakursach.R
import com.example.siegakursach.view.game.match.tabslayout.table.models.TableRows

class TableAdapter(
    private val tableRows: List<TableRows>
) : RecyclerView.Adapter<TableAdapter.MainViewHolder>() {

    companion object {
        var megastatus = false
        var i = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_table, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(tableRows[position])
    }

    override fun getItemCount(): Int = tableRows.size

    class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val nameTeam: TextView = itemView.findViewById(R.id.tv_NameTeam)
        private val number: TextView = itemView.findViewById(R.id.tv_Number)
        private val countGames: TextView = itemView.findViewById(R.id.tv_Games)
        private val points: TextView = itemView.findViewById(R.id.tv_Points)
        private val goals: TextView = itemView.findViewById(R.id.tv_Goals)

        private val bundle = Bundle()

        fun bind(item: TableRows) {
            megastatus = false
//            i+=1
            nameTeam.text = item.team.name
            number.text = (i++).toString()
//            i += 1
            countGames.text = (item.draw.toInt() + item.loss.toInt() + item.win.toInt()).toString()
            points.text = item.points
            goals.text = item.goalsfor

        }
    }
}