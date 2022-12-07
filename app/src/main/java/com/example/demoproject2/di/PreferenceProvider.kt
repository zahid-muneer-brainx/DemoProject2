package com.example.demoproject2.di

import android.content.Context
import com.example.demoproject2.SharedPreferences.MySharedPreferences

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceProvider {
    @Provides
    fun provideSharedPreferenceManager(@ApplicationContext context: Context) =
        MySharedPreferences(context)
}