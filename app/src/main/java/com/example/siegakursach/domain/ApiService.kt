package com.example.siegakursach.domain

import com.example.siegakursach.domain.models.SportEvents
import com.example.siegakursach.domain.models.byday.SportDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.siegakursach.domain.models.bygameid.MatchResult
import com.example.siegakursach.view.game.match.tabslayout.coefficient.models.Coefficient
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2H
import com.example.siegakursach.view.game.match.tabslayout.table.models.TableResponse
import com.example.siegakursach.view.game.model.EndData
import com.example.siegakursach.view.live.models.Live


interface ApiService {

    @GET("get.php?login=Verly&token=50103-7LWGEW20mZ2sDAN&task=enddata")
    suspend fun getPastDay(
        @Query("sport") sport: String,
        @Query("day") data: String
    ): Response<SportEvents>

    @GET("get.php?login=Verly&token=31221-XPeWRDIYTkkbW1H")
    suspend fun getEventsByDay(
        @Query("task") task: String,
        @Query("sport") sport: String,
        @Query("day") day: String
    ): Response<SportDay>

    @GET("get.php?login=Verly&token=31221-XPeWRDIYTkkbW1H&p=1")
    suspend fun getEndDataEvents(
        @Query("task") task: String,
        @Query("sport") sport: String,
        @Query("day") day: String
    ): Response<EndData>

    @GET("get.php?login=Verly&token=31221-XPeWRDIYTkkbW1H&task=eventdata")
    suspend fun getMatchById(
        @Query("game_id") id: String
    ): Response<MatchResult>

    @GET("get.php?login=Verly&token=31221-XPeWRDIYTkkbW1H&task=h2h")
    suspend fun getH2HGames(
        @Query("game_id") id: String
    ): Response<H2H>

    @GET("get.php?login=Verly&token=31221-XPeWRDIYTkkbW1H&task=tabledata")
    suspend fun getTableByLeague(
        @Query("league") id: String
    ): Response<TableResponse>

    @GET("get.php?login=Verly&token=31221-XPeWRDIYTkkbW1H&task=odds")
    suspend fun getCoefficientsById(
        @Query("game_id") id: String
    ): Response<Coefficient>

    @GET("get.php?login=Verly&token=31221-XPeWRDIYTkkbW1H&task=livedata")
    suspend fun getLiveMatches(
        @Query("sport") id: String
    ): Response<Live>

}