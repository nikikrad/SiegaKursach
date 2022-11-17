package com.example.siegakursach.view.live.models

import com.example.siegakursach.domain.models.byday.Away
import com.example.siegakursach.domain.models.byday.Home
import com.example.siegakursach.domain.models.byday.League

data class LiveGames(
    val game_id: String,
    val time: String,
    val league: League,
    val home: Home,
    val away: Away,
    val timer: String,
    val score: String
)
