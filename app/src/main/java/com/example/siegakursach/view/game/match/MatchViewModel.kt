package com.example.siegakursach.view.game.match

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siegakursach.view.game.match.repository.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.siegakursach.domain.models.bygameid.MatchResult


class MatchViewModel(private val matchRepository: MatchRepository): ViewModel() {

    val liveData: MutableLiveData<MatchResult> = MutableLiveData()

    fun getMatch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                liveData.postValue(matchRepository.getMatch(id))
            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }

}