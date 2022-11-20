package com.example.siegakursach.single

object GameData {
    lateinit var gameId: String
    fun gameId(gameId: String){
        this.gameId = gameId
    }
    lateinit var leagueId: String
    fun leagueId(leagueId: String){
        this.leagueId = leagueId
    }
}