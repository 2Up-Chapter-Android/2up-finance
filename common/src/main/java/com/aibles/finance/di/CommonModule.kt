package com.aibles.finance.di

import com.google.gson.Gson
import com.aibles.finance.AppDispatchers
import com.aibles.finance.data.remote.services.CommonService
import com.aibles.finance.data.remote.util.CallAdapterFactory
import com.aibles.finance.data.remote.util.HeaderAuthorizationInterceptor
import com.track.common.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CommonModule {
    private const val BASE_URL = BuildConfig.BASE_URL

    @Provides
    fun provideAppDispatchers() = AppDispatchers(Dispatchers.Main, Dispatchers.IO, Dispatchers.Default)

    @Singleton
    @Provides
    fun provideHttpLogging() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(HeaderAuthorizationInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

    @Singleton
    @Provides
    fun provideGson() =
        Gson().newBuilder()
            .serializeNulls()
            .create()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesCommonService(retrofit: Retrofit): CommonService {
        return retrofit.create(CommonService::class.java)
    }

}