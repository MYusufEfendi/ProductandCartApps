package com.yusuf.app.testtwiscode.di

import android.content.Context
import androidx.room.Room
import com.yusuf.app.testtwiscode.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "Twiscode.db"
    ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun providesProductDao(db: AppDatabase) = db.productDao()

    @Singleton
    @Provides
    fun providesCartDao(db: AppDatabase) = db.cartDao()

    @Singleton
    @Provides
    fun providesFilterDao(db: AppDatabase) = db.filterDao()

}