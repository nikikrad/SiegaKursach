package com.example.siegakursach.view.game.match.repository

import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.domain.models.bygameid.MatchResult

class MatchRepository(private val retrofit: ApiService) {

    suspend fun getMatch(id:String): MatchResult? {
        return retrofit.getMatchById(id).body()
    }
}