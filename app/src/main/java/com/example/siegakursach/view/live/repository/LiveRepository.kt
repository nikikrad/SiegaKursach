package com.example.siegakursach.view.live.repository

import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2H
import com.example.siegakursach.view.live.models.Live

class LiveRepository(private val retrofit: ApiService) {
    suspend fun getLiveMatches(): Live? {
        return retrofit.getLiveMatches().body()
    }
}