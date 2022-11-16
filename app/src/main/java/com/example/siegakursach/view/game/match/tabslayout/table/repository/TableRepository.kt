package com.example.siegakursach.view.game.match.tabslayout.table.repository

import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2H
import com.example.siegakursach.view.game.match.tabslayout.table.models.TableResponse

class TableRepository(private val retrofit: ApiService) {
    suspend fun getTable(id: String): TableResponse? {
        return retrofit.getTableByLeague(id).body()
    }
}