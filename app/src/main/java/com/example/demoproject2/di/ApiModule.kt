package com.example.demoproject2.di


import com.example.demoproject2.BuildConfig
import com.example.demoproject2.SharedPreferences.MySharedPreferences
import com.example.demoproject2.network.ClientDexApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {


    @Provides
    fun providesOkHttpClient(mUserDataStore: MySharedPreferences): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(logging)
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .header("Content-Type", "application/json")
                        .header("access-token", mUserDataStore.token ?: "")
                        .header("client", mUserDataStore.clientid ?: "")
                        .header("uid", mUserDataStore.uid ?: "")
                        .build()
                )
            }.build()
    }
    @Provides
    fun provideRetrofitService1(mUserDataStore: MySharedPreferences): ClientDexApiClient =
        Retrofit.Builder()
            .baseUrl("https://staging.clientdex.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient(mUserDataStore))
            .build()
            .create(ClientDexApiClient::class.java)

}