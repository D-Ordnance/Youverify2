package com.deeosoft.youverifytest2.feature.registration.data.datasource

import android.util.Log
import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.entity.LoginEntity
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import com.deeosoft.youverifytest2.feature.registration.domain.entity.RegistrationEntity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class RegistrationDataSourceImpl: RegistrationDataSource {
    override suspend fun register(model: RegistrationEntity): Resource<OnboardingResponse> {
        var response: OnboardingResponse? = null
        val db = Firebase.firestore

        try {
            Log.e("TAG", "register: here ${model.toHashMap()}", )
            val userCollection = db.collection("User").document("${model.email}${model.password}".trim())
            userCollection.set(model.toHashMap()).addOnSuccessListener {
                response = OnboardingResponse(
                    success = true,
                    valid = true,
                    message = "User has been registered"
                )
            }.addOnFailureListener {
                response = OnboardingResponse(
                    success = false,
                    valid = true,
                    message = it.message!!
                )
            }.await()
            return Resource.Success(response)
        } catch (e: Exception) {
            return Resource.Error("Something went wrong")
        }

    }
}