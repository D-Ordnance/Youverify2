package com.deeosoft.youverifytest2.core.di

import android.content.Context
import com.deeosoft.youverifytest2.core.network.AuthService
import com.deeosoft.youverifytest2.core.network.AuthServiceImpl
import com.deeosoft.youverifytest2.core.network.InternetConnectionService
import com.deeosoft.youverifytest2.core.network.InternetConnectionServiceImpl
import com.google.firebase.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideFirebase(): Firebase = Firebase

    @Provides
    @Singleton
    fun provideAuthService(firebase: Firebase): AuthService = AuthServiceImpl(firebase)

    @Provides
    @Singleton
    fun provideNetworkService(context: Context): InternetConnectionService {
        return InternetConnectionServiceImpl(context = context)
    }
}