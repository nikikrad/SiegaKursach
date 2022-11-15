package com.example.siegakursach.view.main.repository

import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.domain.models.SportEvents
import com.example.siegakursach.domain.models.byday.SportDay

class MainRepository(private val retrofit: ApiService){

    suspend fun getEvents(sport: String, day: String): SportDay? {
        return retrofit.getEventsByDay(sport, day).body()
    }
}