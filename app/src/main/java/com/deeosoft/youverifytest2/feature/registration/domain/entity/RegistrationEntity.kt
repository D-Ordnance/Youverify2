package com.deeosoft.youverifytest2.feature.registration.domain.entity

class RegistrationEntity(
    val name: String,
    val email: String,
    val password: String
){
    fun toHashMap(): HashMap<String, String> =
        hashMapOf("Name" to name, "Email" to email, "password" to password)

    fun something(){

    }
}
