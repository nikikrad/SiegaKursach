package com.example.siegakursach.view.game.match.tabslayout.table

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siegakursach.view.game.match.tabslayout.h2h.model.H2H
import com.example.siegakursach.view.game.match.tabslayout.h2h.repository.H2HRepository
import com.example.siegakursach.view.game.match.tabslayout.table.models.TableResponse
import com.example.siegakursach.view.game.match.tabslayout.table.repository.TableRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TableViewModel(private val tableRepository: TableRepository):ViewModel() {

    val liveData: MutableLiveData<TableResponse> = MutableLiveData()

    fun getMatch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                liveData.postValue(tableRepository.getTable(id))
            }catch (e:Exception){
                Log.e("Error: ", e.toString())
            }
        }
    }

}