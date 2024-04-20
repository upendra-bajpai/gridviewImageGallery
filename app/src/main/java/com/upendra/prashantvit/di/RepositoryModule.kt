package com.upendra.prashantvit.di

import com.upendra.prashantvit.data.repository.ImageRepository
import com.upendra.prashantvit.data.repository.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Upendra on 19/2/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(userRepository: ImageRepositoryImpl): ImageRepository
}