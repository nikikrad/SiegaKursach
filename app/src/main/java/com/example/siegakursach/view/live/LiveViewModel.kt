package com.example.siegakursach.view.live

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2H
import com.example.siegakursach.view.game.match.tabslayout.h2h.repository.H2HRepository
import com.example.siegakursach.view.live.models.Live
import com.example.siegakursach.view.live.repository.LiveRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LiveViewModel(private val liveRepository: LiveRepository): ViewModel() {

    val liveData: MutableLiveData<Live> = MutableLiveData()

    fun getMatch() {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                liveData.postValue(liveRepository.getLiveMatches())
            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }

}