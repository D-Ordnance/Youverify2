package com.deeosoft.youverifytest2.core.network

interface InternetConnectionService {
    suspend fun hasInternetConnection(): Boolean
}