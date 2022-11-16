package com.example.siegakursach.domain

import com.example.siegakursach.domain.models.SportEvents
import com.example.siegakursach.domain.models.byday.SportDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.siegakursach.domain.models.bygameid.MatchResult


interface ApiService {

    @GET("get.php?login=moiseenko&token=50103-7LWGEW20mZ2sDAN&task=pre&bookmaker=bet365")
    suspend fun getSportEvents(
        @Query("sport") sport: String
    ): Response<SportEvents>

    @GET("get.php?login=moiseenko&token=50103-7LWGEW20mZ2sDAN&task=predata")
    suspend fun getEventsByDay(
        @Query("sport") sport: String,
        @Query("day") day: String
    ): Response<SportDay>

    @GET("get.php?login=moiseenko&token=50103-7LWGEW20mZ2sDAN&task=eventdata")
    suspend fun getMatchById(
        @Query("game_id") id: String
    ): Response<MatchResult>

}