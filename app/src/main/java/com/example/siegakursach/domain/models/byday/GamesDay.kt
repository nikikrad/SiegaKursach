package com.example.siegakursach.domain.models.byday

data class GamesDay(
    val game_id: String,
    val time: String,
    val time_status: String,
    val league: League,
    val home: Home,
    val away: Away
)
