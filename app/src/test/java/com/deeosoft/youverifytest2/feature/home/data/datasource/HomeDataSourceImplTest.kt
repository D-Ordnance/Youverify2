package com.deeosoft.youverifytest2.feature.home.data.datasource

import com.deeosoft.youverifytest2.core.helper.db.ProductDao
import com.deeosoft.youverifytest2.core.helper.db.ProductDatabase
import com.deeosoft.youverifytest2.core.network.InternetConnectionService
import com.deeosoft.youverifytest2.core.network.NetworkService
import com.deeosoft.youverifytest2.core.network.ServerProduct
import com.deeosoft.youverifytest2.feature.login.data.datasource.OnboardingResponse
import com.deeosoft.youverifytest2.feature.login.domain.repository.Resource
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomeDataSourceImplTest {
    @Mock
    private lateinit var database: ProductDatabase
    @Mock
    private lateinit var dao: ProductDao
    @Mock
    private lateinit var internetService: InternetConnectionService
    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var dataSource: HomeDataSource

    private val result = listOf(
        ServerProduct(
            id = 1,
            title = "Test",
            price = 30.21F,
            description = "Test Test",
            category = "Test Category",
            image = "Test Image"
        ),
        ServerProduct(
            id = 2,
            title = "Test2",
            price = 30.22F,
            description = "Test2 Test2",
            category = "Test2 Category2",
            image = "Test2 Image2"
        ),
        ServerProduct(
            id = 3,
            title = "Tes3",
            price = 30.23F,
            description = "Tes3 Test3",
            category = "Test3 Category3",
            image = "Test3 Image3"
        ),
    )

    private val sourceSuccessStubResult = Resource.Success(
        result
    )
    private val sourceErrorStubResult = Resource.Error<OnboardingResponse>(
        "No Internet Connection"
    )

    @BeforeEach
    fun setUp(){
        MockitoAnnotations.openMocks(this)

        dataSource = HomeDataSourceImpl(internetService, database, networkService)
    }

    @DisplayName("should return [Resource.Success] if internet is true and  db returns success")
    @Test
    fun shouldReturnSuccessWheNInternetIsTrueAndDBReturnsSuccess() =  runTest {
        Mockito.`when`(internetService.hasInternetConnection()).thenAnswer{true}
        Mockito.`when`(networkService.getProducts("5")).thenAnswer{result}
        Mockito.`when`(database.productDao()).thenReturn(dao)
        Mockito.`when`(dao.getProduct()).thenAnswer{result}

        val actual = dataSource.remoteSource()

        Mockito.verify(database, Mockito.times(2)).productDao()
        Mockito.verify(dao, Mockito.times(1)).getProduct()
        Mockito.verify(networkService, Mockito.times(1)).getProducts("5")
        Assertions.assertEquals(sourceSuccessStubResult.data, actual.data)
    }
}