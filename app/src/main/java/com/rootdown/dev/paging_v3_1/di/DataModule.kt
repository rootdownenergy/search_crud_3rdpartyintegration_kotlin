package com.rootdown.dev.paging_v3_1.di

import android.content.Context
import androidx.room.Room
import com.rootdown.dev.paging_v3_1.db.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRDDB(@ApplicationContext context: Context): RepoDatabase {
        return Room.databaseBuilder(
            context,
            RepoDatabase::class.java,
            "appdb"
        ).build()
    }
}
