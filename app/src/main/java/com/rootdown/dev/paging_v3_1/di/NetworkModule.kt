package com.rootdown.dev.paging_v3_1.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rootdown.dev.paging_v3_1.api.ApiServiceStrain
import com.rootdown.dev.paging_v3_1.api.RootDownService
import com.rootdown.dev.paging_v3_1.di.util.NetUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    @Named("strainApi")
    fun provideRetrofitStrain(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(NetUtil.strainApi)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    @Named("repoApi")
    fun provideRetrofitRepo(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(NetUtil.repoApi)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideApiServiceStrain(@Named("strainApi") retrofit: Retrofit): ApiServiceStrain = retrofit.create(ApiServiceStrain::class.java)

    @Provides
    @Singleton
    fun provideRootDownService(@Named("repoApi") retrofit: Retrofit): RootDownService = retrofit.create(RootDownService::class.java)
}