package com.yusuf.app.testtwiscode.di

import com.yusuf.app.testtwiscode.db.CartDao
import com.yusuf.app.testtwiscode.db.FilterDao
import com.yusuf.app.testtwiscode.db.ProductDao
import com.yusuf.app.testtwiscode.network.ApiService
import com.yusuf.app.testtwiscode.ui.cart.CartRepository
import com.yusuf.app.testtwiscode.ui.product.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideProductRepository(apiService: ApiService, productDao: ProductDao,filterDao: FilterDao): ProductRepository {
        return ProductRepository(apiService, productDao,filterDao)
    }

    @Provides
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepository(cartDao)
    }

}