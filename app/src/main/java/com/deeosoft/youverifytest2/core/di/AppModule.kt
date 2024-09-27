package com.deeosoft.youverifytest2.core.di


import android.content.Context
import com.deeosoft.youverifytest2.core.helper.db.ProductDatabase
import com.deeosoft.youverifytest2.core.network.InternetConnectionService
import com.deeosoft.youverifytest2.core.network.InternetConnectionServiceImpl
import com.deeosoft.youverifytest2.core.network.NetworkService
import com.deeosoft.youverifytest2.feature.home.data.datasource.HomeDataSource
import com.deeosoft.youverifytest2.feature.home.data.datasource.HomeDataSourceImpl
import com.deeosoft.youverifytest2.feature.home.data.repository.HomeRepositoryImpl
import com.deeosoft.youverifytest2.feature.home.domain.repository.HomeRepository
import com.deeosoft.youverifytest2.feature.home.domain.usecase.ProductUseCase
import com.deeosoft.youverifytest2.feature.login.data.datasource.LoginDataSource
import com.deeosoft.youverifytest2.feature.login.data.datasource.LoginDataSourceImpl
import com.deeosoft.youverifytest2.feature.login.data.repository.LoginRepositoryImpl
import com.deeosoft.youverifytest2.feature.login.domain.repository.LoginRepository
import com.deeosoft.youverifytest2.feature.login.domain.usecase.LoginUseCase
import com.deeosoft.youverifytest2.feature.registration.data.datasource.RegistrationDataSource
import com.deeosoft.youverifytest2.feature.registration.data.datasource.RegistrationDataSourceImpl
import com.deeosoft.youverifytest2.feature.registration.data.repository.RegistrationRepositoryImpl
import com.deeosoft.youverifytest2.feature.registration.domain.repository.RegistrationRepository
import com.deeosoft.youverifytest2.feature.registration.domain.usecase.RegistrationUseCase
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginDataSource(db: FirebaseFirestore): LoginDataSource {
        return LoginDataSourceImpl(db)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(internetService: InternetConnectionService, dataSource: LoginDataSource): LoginRepository {
        return LoginRepositoryImpl(internetService, dataSource)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: LoginRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegistrationDataSource(fireStore: FirebaseFirestore): RegistrationDataSource {
        return RegistrationDataSourceImpl(fireStore)
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(internetService: InternetConnectionService, dataSource: RegistrationDataSource): RegistrationRepository {
        return RegistrationRepositoryImpl(internetService, dataSource)
    }

    @Provides
    @Singleton
    fun provideRegistrationUseCase(repository: RegistrationRepository): RegistrationUseCase {
        return RegistrationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideHomeDataSource(internetService: InternetConnectionService, db: ProductDatabase, networkService: NetworkService): HomeDataSource {
        return HomeDataSourceImpl(internetService, db, networkService)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(dataSource: HomeDataSource): HomeRepository {
        return HomeRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideProductUseCase(repository: HomeRepository): ProductUseCase {
        return ProductUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore {
        return Firebase.firestore
    }
}