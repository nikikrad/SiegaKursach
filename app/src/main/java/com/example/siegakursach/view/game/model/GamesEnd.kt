package com.example.siegakursach.view.game.model

import com.example.siegakursach.domain.models.byday.Away
import com.example.siegakursach.domain.models.byday.Home
import com.example.siegakursach.domain.models.byday.League

data class GamesEnd(
    val game_id: String,
    val time: String,
    val time_status: String,
    val league: League,
    val home: Home,
    val away: Away,
    val score: String
)
