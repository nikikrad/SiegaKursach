package com.example.siegakursach.view.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siegakursach.view.main.repository.MainRepository
import com.example.siegakursach.domain.models.SportEvents
import com.example.siegakursach.domain.models.byday.SportDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    val liveData: MutableLiveData<SportDay> = MutableLiveData()

    fun getGameEvents(sport: String, day:String) {
        viewModelScope.launch(Dispatchers.IO) {
            try{

                liveData.postValue(mainRepository.getEvents(sport, day))
            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }
}