package com.deeosoft.youverifytest2.feature.login.data.datasource

import android.util.Log
import com.deeosoft.youverifytest2.feature.login.domain.entity.LoginEntity
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class LoginDataSourceImpl(): LoginDataSource {

    var response: OnboardingResponse? = null
    override suspend fun loginWithEmail(model: LoginEntity): Resource<OnboardingResponse> {
        val db = Firebase.firestore

        try {

                db.collection("User").get().addOnSuccessListener {
                    for (document in it) {
                        if("${model.email}${model.password}".trim().equals("${document.data["Email"]}${document.data["password"]}".trim(), false) ) {
                            Log.e("TAG", "DataSource exist: ${document.id} => ${document.data}")
                            response = OnboardingResponse(
                                success = true,
                                valid = true,
                                message = "Successful"
                            )
                        }
                    }
                }.addOnFailureListener {
                    Log.w("TAG", "Error getting documents.", it)
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