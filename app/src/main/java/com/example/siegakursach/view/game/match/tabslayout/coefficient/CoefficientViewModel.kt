package com.example.siegakursach.view.game.match.tabslayout.coefficient

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siegakursach.view.game.match.tabslayout.coefficient.models.Coefficient
import com.example.siegakursach.view.game.match.tabslayout.coefficient.repository.CoefficientRepository
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2H
import com.example.siegakursach.view.game.match.tabslayout.h2h.repository.H2HRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoefficientViewModel(private val coefficientRepository: CoefficientRepository): ViewModel() {

    val liveData: MutableLiveData<Coefficient> = MutableLiveData()

    fun getMatch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                liveData.postValue(coefficientRepository.getCoefficientsById(id))
            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }

}