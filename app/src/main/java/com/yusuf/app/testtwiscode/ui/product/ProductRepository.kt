package com.yusuf.app.testtwiscode.ui.product

import com.yusuf.app.testtwiscode.db.FilterDao
import com.yusuf.app.testtwiscode.db.ProductDao
import com.yusuf.app.testtwiscode.model.CategoryTable
import com.yusuf.app.testtwiscode.model.ProductResponse
import com.yusuf.app.testtwiscode.model.ProductTable
import com.yusuf.app.testtwiscode.model.ShortBytable
import com.yusuf.app.testtwiscode.network.ApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService,
    private val productDao: ProductDao,
    private val filterDao: FilterDao
) {
    suspend fun fetchProduct(): Response<List<ProductResponse>> {
        return api.getAll()
    }

    fun getProduct(categoryId:String = ""): Flow<List<ProductTable>> {
        return productDao.getAll(categoryId)
    }

    suspend fun insertProduct(products: ProductTable){
        return productDao.insert(products)
    }

    suspend fun countProduct():Int{
        return productDao.countBalance()
    }

    suspend fun insertCategory(categoryTable: CategoryTable){
        return filterDao.insertCategory(categoryTable)
    }

    suspend fun getCategory(): List<CategoryTable> {
        return filterDao.getAllCategory()
    }

    fun getSelectedCategory(): CategoryTable {
        return filterDao.getSelectedCategory()
    }

    fun updateStatusCategory(isSelected:Boolean) {
        return filterDao.updateStatusCategory(isSelected)
    }


    // short by

    suspend fun insertShortBy(shortBytable: ShortBytable){
        return filterDao.insertShortBy(shortBytable)
    }

    suspend fun getShortBy(): List<ShortBytable> {
        return filterDao.getAllShortBy()
    }

    fun getSelectedShortBy(): ShortBytable {
        return filterDao.getSelectedShortBy()
    }

    fun updateStatusShortBy(isSelected:Boolean) {
        return filterDao.updateStatusShortBy(isSelected)
    }
}