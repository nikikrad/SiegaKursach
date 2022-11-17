package com.example.siegakursach.view.game.match.tabslayout.coefficient.repository

import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.view.game.match.tabslayout.coefficient.models.Coefficient

class CoefficientRepository(private val retrofit: ApiService) {
    suspend fun getCoefficientsById(id: String): Coefficient? {
        return retrofit.getCoefficientsById(id).body()
    }
}