package com.example.siegakursach.view.main.repository

import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.domain.models.SportEvents
import com.example.siegakursach.domain.models.byday.SportDay
import com.example.siegakursach.view.game.model.EndData

class MainRepository(private val retrofit: ApiService){

    suspend fun getEvents(task:String, sport: String, day: String): SportDay? {
        return retrofit.getEventsByDay(task, sport, day).body()
    }

    suspend fun getEndDataEvents(task:String, sport: String, day: String): EndData? {
        return retrofit.getEndDataEvents(task, sport, day).body()
    }
}