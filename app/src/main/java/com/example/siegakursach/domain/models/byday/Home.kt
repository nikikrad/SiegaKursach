package com.example.siegakursach.domain.models.byday

data class Home(
    val name: String,
    val id: String,
    val image_id: String,
    val cc: String
){
    override fun hashCode(): Int {
        try {
            return super.hashCode()
        }catch (e: Exception){
            return e.hashCode()
        }
    }
}
