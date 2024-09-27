package com.deeosoft.youverifytest2.feature.login.data.datasource

import com.deeosoft.youverifytest2.feature.login.domain.entity.LoginEntity
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginDataSourceImplTest{
    @Mock
    private lateinit var firebaseFireStore: FirebaseFirestore

    private lateinit var dataSource: LoginDataSource


    private val sourceSuccessStubResult = Resource.Success(
        OnboardingResponse(
            valid = false,
            success = true,
            message = "Successful"
        )
    )
    private val sourceErrorStubResult = Resource.Error<OnboardingResponse>(
        "No Internet Connection"
    )

    private val entity = LoginEntity(email = "Dolapo Olakanmi", password = "password")

    @BeforeEach
    fun setUp(){
        MockitoAnnotations.openMocks(this)

        dataSource = LoginDataSourceImpl(firebaseFireStore)
    }

    @DisplayName("should return [Resource.Success] if fireStore db returns success")
    @Test
    fun shouldReturnSuccessWhenFireStoreIsSuccess() =  runTest {
//        Mockito.`when`(firebaseFireStore.collection("User")).thenAnswer()
        Mockito.`when`(firebaseFireStore.collection("User").get().isSuccessful).thenReturn(true)

        val actual = dataSource.loginWithEmail(entity)

        Assertions.assertEquals(sourceSuccessStubResult.data, actual.data)
    }
}