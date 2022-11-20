package com.example.siegakursach.data.di

import com.example.siegakursach.domain.instance.RetrofitInstance
import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.view.game.match.MatchViewModel
import com.example.siegakursach.view.game.match.repository.MatchRepository
import com.example.siegakursach.view.game.match.tabslayout.coefficient.CoefficientViewModel
import com.example.siegakursach.view.game.match.tabslayout.coefficient.repository.CoefficientRepository
import com.example.siegakursach.view.game.match.tabslayout.h2h.repository.H2HRepository
import com.example.siegakursach.view.game.match.tabslayout.h2h.H2HViewModel
import com.example.siegakursach.view.game.match.tabslayout.table.TableViewModel
import com.example.siegakursach.view.game.match.tabslayout.table.repository.TableRepository
import com.example.siegakursach.view.live.LiveViewModel
import com.example.siegakursach.view.live.match.LiveMatchViewModel
import com.example.siegakursach.view.live.repository.LiveRepository
import com.example.siegakursach.view.main.MainViewModel
import com.example.siegakursach.view.main.repository.MainRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { MainRepository(get()) }

    viewModel { MainViewModel(get()) }

    single { MatchRepository(get()) }

    viewModel{ MatchViewModel(get()) }

    single { H2HRepository(get()) }

    viewModel { H2HViewModel(get()) }

    single { TableRepository(get()) }

    viewModel { TableViewModel(get()) }

    single { CoefficientRepository(get()) }

    viewModel { CoefficientViewModel(get()) }

    single{ LiveRepository(get()) }

    viewModel { LiveViewModel(get()) }

    viewModel { LiveMatchViewModel(get()) }
}

val retrofitModule = module{

    fun provideRetrofit(): ApiService {
        return RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    }

    single { provideRetrofit() }
}