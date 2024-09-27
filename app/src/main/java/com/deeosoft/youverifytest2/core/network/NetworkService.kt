package com.deeosoft.youverifytest2.core.network

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: String = "20"
    ): List<ServerProduct>
}

@Entity(tableName = "Product",
    indices = [
        Index(
            value = ["id", "image"],
            unique = true)]
)
data class ServerProduct(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Float,
    val description: String,
    val category: String,
    val image: String,
)

data class Rating(
    val rate: Float,
    val count: Int
)