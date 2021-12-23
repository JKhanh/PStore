package com.aibles.pstore.di

import android.content.Context
import com.aibles.pstore.data.local.CartDao
import com.aibles.pstore.data.local.PStoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PStoreDatabase =
        PStoreDatabase.buildDatabase(context)

    @Singleton
    @Provides
    fun provideCartDao(database: PStoreDatabase): CartDao =
        database.cartDao()
}