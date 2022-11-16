package com.example.siegakursach.view.game.match.tabslayout.h2h

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2H
import com.example.siegakursach.view.game.match.tabslayout.h2h.repository.H2HRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class H2HViewModel(private val h2HRepository: H2HRepository):ViewModel() {

    val liveData: MutableLiveData<H2H> = MutableLiveData()

    fun getMatch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                liveData.postValue(h2HRepository.getH2HGames(id))
            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }

}