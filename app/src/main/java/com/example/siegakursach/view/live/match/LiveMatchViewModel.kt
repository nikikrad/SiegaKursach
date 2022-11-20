package com.example.siegakursach.view.live.match

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siegakursach.view.live.repository.LiveRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.siegakursach.domain.models.bygameid.MatchResult

class LiveMatchViewModel(private val liveRepository: LiveRepository): ViewModel() {

    val liveData: MutableLiveData<MatchResult> = MutableLiveData()

    fun getMatch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                liveData.postValue(liveRepository.getMatch(id))
            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }

}