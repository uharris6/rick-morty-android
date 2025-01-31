package com.globant.rickmorty.di

import com.globant.rickmorty.data.CharactersRepo
import com.globant.rickmorty.data.services.CharactersService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor
}

private fun providesHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .build()


private fun providesGson(): Gson = GsonBuilder().create()


private fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


private fun providesCharactersService(retrofit: Retrofit): CharactersService =
    retrofit.create(CharactersService::class.java)


val dataModule = module {
    single { providesGson() }
    single { providesHttpClient() }
    single { providesRetrofit(get(), get()) }
    single { providesCharactersService(get()) }
    single { CharactersRepo(get(), get(named("ioDispatcher"))) }
    single(named("ioDispatcher")) {
        Dispatchers.IO
    }
}