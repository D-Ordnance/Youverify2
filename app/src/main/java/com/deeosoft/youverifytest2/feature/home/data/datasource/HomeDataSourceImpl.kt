package com.deeosoft.youverifytest2.feature.home.data.datasource

import android.database.sqlite.SQLiteConstraintException
import com.deeosoft.youverifytest2.core.helper.db.ProductDatabase
import com.deeosoft.youverifytest2.core.network.InternetConnectionService
import com.deeosoft.youverifytest2.core.network.NetworkService
import com.deeosoft.youverifytest2.core.network.ServerProduct
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val internetService: InternetConnectionService,
    private val database: ProductDatabase,
    private val networkService: NetworkService
): HomeDataSource {
    override suspend fun remoteSource(): Resource<List<ServerProduct?>> =
        if(internetService.hasInternetConnection()) {
            try {
                val response = networkService.getProducts()
                if (response != null) {
                    saveToLocal(response)
                    localSource()
                } else {
                    Resource.Error("An error occurred")
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                Resource.Error(ex.message!!)
            }
        }else{
            Resource.Error("No Internet Connection")
        }

    override suspend fun localSource(): Resource<List<ServerProduct?>> =
        try {
            val response = database.productDao().getProduct()
            Resource.Success(response)
        }catch (ex: Exception){
            if(ex is SQLiteConstraintException){
                Resource.Error("No new Update")
            }else{
                Resource.Error("An Error Occurred\nSwipe to refresh")
            }
        }

    private suspend fun saveToLocal(data: List<ServerProduct>){
        database.productDao().insert(data.asReversed())
    }
}