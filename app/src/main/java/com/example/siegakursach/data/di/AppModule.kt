package com.example.siegakursach.data.di

import com.example.siegakursach.domain.instance.RetrofitInstance
import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.view.game.match.MatchViewModel
import com.example.siegakursach.view.game.match.repository.MatchRepository
import com.example.siegakursach.view.main.MainViewModel
import com.example.siegakursach.view.main.repository.MainRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { MainRepository(get()) }

    viewModel { MainViewModel(get()) }

    single { MatchRepository(get()) }

    viewModel{ MatchViewModel(get()) }

}

val retrofitModule = module{

    fun provideRetrofit(): ApiService {
        return RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    }

    single { provideRetrofit() }
}