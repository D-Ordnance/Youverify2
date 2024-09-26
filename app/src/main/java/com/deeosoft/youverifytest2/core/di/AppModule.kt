package com.deeosoft.youverifytest2.core.di


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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginDataSource(): LoginDataSource {
        return LoginDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideLoginRepository(dataSource: LoginDataSource): LoginRepository {
        return LoginRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: LoginRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegistrationDataSource(): RegistrationDataSource {
        return RegistrationDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(dataSource: RegistrationDataSource): RegistrationRepository {
        return RegistrationRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideRegistrationUseCase(repository: RegistrationRepository): RegistrationUseCase {
        return RegistrationUseCase(repository)
    }
}