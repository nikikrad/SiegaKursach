package com.example.siegakursach.view.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siegakursach.view.main.repository.MainRepository
import com.example.siegakursach.domain.models.SportEvents
import com.example.siegakursach.domain.models.byday.SportDay
import com.example.siegakursach.view.game.model.EndData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    val liveData: MutableLiveData<SportDay> = MutableLiveData()
    val endData: MutableLiveData<EndData> = MutableLiveData()

    fun getGameEvents(task: String, sport: String, day:String) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                if (task == "predata"){
                    liveData.postValue(mainRepository.getEvents(task, sport, day))
                }
                else
                    endData.postValue(mainRepository.getEndDataEvents(task, sport, day))

            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }
}