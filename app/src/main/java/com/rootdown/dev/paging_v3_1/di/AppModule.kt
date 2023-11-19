package com.rootdown.dev.paging_v3_1.di

import com.rootdown.dev.paging_v3_1.api.ApiServiceStrain
import com.rootdown.dev.paging_v3_1.api.RootDownService
import com.rootdown.dev.paging_v3_1.db.RepoDatabase
import com.rootdown.dev.paging_v3_1.repo.*
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
    fun provideRootDownRepository(
        service: RootDownService,
        db: RepoDatabase
    ) = RootDownRepository(service,db)

    @Provides
    @Singleton
    fun provideStrainsRepository(
        serviceStrain: ApiServiceStrain,
        db: RepoDatabase
    ) = StrainsRepository(serviceStrain,db)

    @Provides
    @Singleton
    fun provideProfileRepository(
        db: RepoDatabase
    ) = ProfileRepository(db)

    @Provides
    @Singleton
    fun provideUserContent(
        db: RepoDatabase
    ) = UserContent(db)

    @Provides
    @Singleton
    fun provideMapsRepo(
        service: RootDownService,
        db: RepoDatabase
    ) = MapsRepo(service,db)
}