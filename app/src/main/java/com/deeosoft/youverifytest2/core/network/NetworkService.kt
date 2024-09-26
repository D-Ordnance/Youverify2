package com.deeosoft.youverifytest2.core.network

interface NetworkService {
    suspend fun hasInternetConnection(): Boolean
}