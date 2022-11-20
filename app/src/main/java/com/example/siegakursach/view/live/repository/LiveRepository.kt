package com.example.siegakursach.view.live.repository

import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.domain.models.bygameid.MatchResult
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2H
import com.example.siegakursach.view.live.models.Live

class LiveRepository(private val retrofit: ApiService) {
    suspend fun getLiveMatches(sport:String): Live? {
        return retrofit.getLiveMatches(sport).body()
    }

    suspend fun getMatch(id:String): MatchResult? {
        return retrofit.getMatchById(id).body()
    }
}