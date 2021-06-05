package com.example.receipeapp.di

import com.example.receipeapp.api.ApiService
import com.example.receipeapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReceipeModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance() : ApiService = Retrofit.Builder()
                                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                                .build()
        .create(ApiService::class.java)
}