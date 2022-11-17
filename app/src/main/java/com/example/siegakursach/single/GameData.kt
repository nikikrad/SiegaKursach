package com.example.siegakursach.single

object GameData {
    lateinit var gameId: String
    fun gameId(gameId: String){
        GameId.gameId = gameId
    }
    lateinit var leagueId: String
    fun leagueId(leagueId: String){
        GameId.leagueId = leagueId
    }
}