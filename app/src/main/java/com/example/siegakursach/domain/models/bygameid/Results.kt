package com.example.siegakursach.domain.models.bygameid

import com.example.siegakursach.domain.models.byday.Away
import com.example.siegakursach.domain.models.byday.Home
import com.example.siegakursach.domain.models.byday.League

data class Results(
    val sport_id: String,
    val time: String,
    val time_status: String,
    val league: League,
    val home: Home,
    val away: Away
)