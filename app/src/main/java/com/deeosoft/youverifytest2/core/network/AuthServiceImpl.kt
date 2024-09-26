package com.deeosoft.youverifytest2.core.network

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.initialize
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(private val firebase: Firebase): AuthService {
    override fun initialize(context: Context) {
        firebase.initialize(context)
    }
}