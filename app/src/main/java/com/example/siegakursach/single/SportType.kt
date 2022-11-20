package com.example.siegakursach.single

object SportType {
    lateinit var sportId: String
    fun sportId(sportId: String){
        this.sportId = sportId
    }

    lateinit var currentSport : String

    val sportList: MutableList<String> = emptyList<String>().toMutableList()

    fun fullySportList(){
        sportList.clear()
        sportList.add("soccer")
        sportList.add("basketball")
        sportList.add("icehockey")
        sportList.add("tennis")
        sportList.add("volleyball")
        sportList.add("tabletennis")
        sportList.add("handball")
        sportList.add("esport")
    }

    fun getSport(): String{
        currentSport = sportList[sportId.toInt()]
        return currentSport
    }
}