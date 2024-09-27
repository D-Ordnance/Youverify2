package com.deeosoft.youverifytest2.core.helper.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deeosoft.youverifytest2.core.network.ServerProduct

@Dao
interface ProductDao {
    @Query("SELECT * from Product ORDER BY id DESC")
    suspend fun getProduct(): List<ServerProduct?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: ServerProduct)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: List<ServerProduct>)
}