package com.deeosoft.youverifytest2

import android.app.Application
import com.deeosoft.youverifytest2.core.network.AuthService
import com.deeosoft.youverifytest2.core.network.AuthServiceImpl
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.initialize
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class YouVerifyTest: Application() {}