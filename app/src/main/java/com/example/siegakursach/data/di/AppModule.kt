package com.example.improvedcrypto.files.data.module

import com.example.siegakursach.domain.instance.RetrofitInstance
import com.example.siegakursach.domain.ApiService
import com.example.siegakursach.view.main.MainViewModel
import com.example.siegakursach.view.main.repository.MainRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { MainRepository(get()) }

    viewModel { MainViewModel(get()) }
//
//    viewModel { SportDescriptionViewModel(get())}
//
//    single {SportRepository(get())}
//
//    single{SearchRepository(get())}
//
//    viewModel{SearchViewModel(get())}

}

val retrofitModule = module{

    fun provideRetrofit(): ApiService {
        return RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    }

    single { provideRetrofit() }
}