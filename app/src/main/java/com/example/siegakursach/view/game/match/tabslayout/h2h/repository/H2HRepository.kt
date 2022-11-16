package com.example.siegakursach.view.game.match.tabslayout.h2h.repository

import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2H

class H2HRepository(private val retrofit: ApiService) {
    suspend fun getH2HGames(id: String): H2H? {
        return retrofit.getH2HGames(id).body()
    }
}