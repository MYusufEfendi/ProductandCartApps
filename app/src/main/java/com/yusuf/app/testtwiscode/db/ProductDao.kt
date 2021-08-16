package com.yusuf.app.testtwiscode.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusuf.app.testtwiscode.model.ProductTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: ProductTable)

    @Query("SELECT * FROM productTable where category_id LIKE '%' || :categoryId || '%' order by price asc ")
    fun getAll(categoryId:String = "" ): Flow<List<ProductTable>>

    @Query("SELECT * FROM productTable where category_id LIKE '%' || :categoryId || '%' order by price desc ")
    fun getAlldesc(categoryId:String = "" ): Flow<List<ProductTable>>

    @Query("SELECT count(*) FROM productTable  ")
    suspend fun countBalance(): Int

}