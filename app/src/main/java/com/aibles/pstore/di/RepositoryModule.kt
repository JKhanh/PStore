package com.aibles.pstore.di

import com.aibles.pstore.data.remote.ProductService
import com.aibles.pstore.data.repository.ProductRepository
import com.aibles.pstore.data.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository
}