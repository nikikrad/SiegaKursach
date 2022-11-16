package com.example.siegakursach.view.game.match.tabslayout.h2h.model

import com.example.siegakursach.domain.models.byday.Away
import com.example.siegakursach.domain.models.byday.Home
import com.example.siegakursach.domain.models.byday.League

data class H2HData(
    var id: String,
    var sport_id: String,
    var league: League,
    var home: Home,
    var away: Away,
    var time: String,
    var ss: String

)
