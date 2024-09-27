package com.deeosoft.youverifytest2.core.di

import android.content.Context
import androidx.room.Room
import com.deeosoft.youverifytest2.core.helper.db.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDataBaseObject(context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "top_head_lines"
        ).build()
    }
}