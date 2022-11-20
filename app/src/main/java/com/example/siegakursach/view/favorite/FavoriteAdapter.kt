package com.example.siegakursach.view.favorite

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.siegakursach.R
import com.example.siegakursach.domain.models.byday.GamesDay
import com.example.siegakursach.single.GameId
import com.example.siegakursach.view.favorite.auth.model.GameRequest
import com.example.siegakursach.view.game.GameAdapter
import java.time.Instant
import java.time.ZoneId

class FavoriteAdapter(
    private val movieList: List<GameRequest>
) : RecyclerView.Adapter<FavoriteAdapter.MainViewHolder>() {

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
        private val favorite: ImageView = itemView.findViewById(R.id.btn_Favorite)

        private val bundle = Bundle()

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: GameRequest) {
            megastatus = false

            try {

                homeTeam.text = item.game_id
                awaiTeam.text = item.away
                val timestamp = Instant.ofEpochSecond(item.time.toLong())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
                time.text = "${timestamp.hour}:${timestamp.minute}0"

            } catch (e: Exception) {
                Log.e("TAG", e.localizedMessage.toString())
            }

            itemView.setOnClickListener {
                if (megastatus == false) {
                    megastatus = true
                    bundle.putInt("STATUS", 0)
//                    GameId.leagueId(item.league.id)
                    GameId.gameId(item.game_id)
                    Navigation.findNavController(itemView)
                        .navigate(R.id.action_favoriteFragment_to_matchFragment, bundle)
                }
            }

        }
    }
}