package com.deeosoft.youverifytest2.core.helper.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deeosoft.youverifytest2.core.network.ServerProduct

@Database(
    entities = [ServerProduct::class],
    version = 1,
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}