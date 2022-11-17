package com.example.siegakursach.single

object GameId {
    lateinit var gameId: String
    fun gameId(gameId: String){
        GameId.gameId = gameId
    }
    lateinit var leagueId: String
    fun leagueId(leagueId: String){
        GameId.leagueId = leagueId
    }
}