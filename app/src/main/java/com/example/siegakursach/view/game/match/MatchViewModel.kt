package com.example.siegakursach.view.game.match

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siegakursach.R
import com.example.siegakursach.view.game.match.repository.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.siegakursach.domain.models.bygameid.MatchResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MatchViewModel(private val matchRepository: MatchRepository) : ViewModel() {

    val match: MutableLiveData<MatchResult> = MutableLiveData()

    fun getMatch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                match.postValue(matchRepository.getMatch(id))
            } catch (e: Exception) {
                Log.e("Error: ", e.toString())
            }
        }
    }

    val status: MutableLiveData<Boolean> = MutableLiveData()

    fun processingData(id: String){
        var auth = FirebaseAuth.getInstance()
        var database = Firebase.database.reference
        var state = false
        viewModelScope.launch(Dispatchers.IO) {
//            status.postValue(false)
            database.child(auth.currentUser?.email.toString().substringBefore("@"))
                .get()
                .addOnSuccessListener {
                    it.children.forEach { data ->
                        if (data.key.toString() == id) {
                            state = true
                        }
                    }
                    status.postValue(state)
                }
        }

    }
}