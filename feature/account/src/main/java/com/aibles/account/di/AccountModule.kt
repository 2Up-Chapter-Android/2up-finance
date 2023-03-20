package com.aibles.account.di

import com.aibles.account.data.remote.RegisterService
import com.aibles.account.data.remote.repository.RegisterRepositoryImpl
import com.aibles.account.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AccountModule {

    @Singleton
    @Provides
    fun provideRegisterService(retrofit: Retrofit): RegisterService {
        return retrofit.create(RegisterService::class.java)
    }

}

@InstallIn(SingletonComponent::class)
@Module
abstract class AccountRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRegisterRepository(impl: RegisterRepositoryImpl): RegisterRepository
}