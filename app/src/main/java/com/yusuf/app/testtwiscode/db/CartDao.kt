package com.yusuf.app.testtwiscode.db

import androidx.room.*
import com.yusuf.app.testtwiscode.model.CartTable
import com.yusuf.app.testtwiscode.model.ProductTable
import com.yusuf.app.testtwiscode.model.TransactionWithProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: CartTable)

    @Query("SELECT * FROM CartTable order by id Asc")
    fun getAll(): Flow<List<CartTable>>

    @Query("SELECT qty FROM CartTable where id_product = :id_product ")
    suspend fun getQtyProduct(id_product: String): Int

    @Transaction
    @Query("select * from CartTable  ")
    suspend fun getListCart(): List<TransactionWithProduct>

    @Query("Delete FROM CartTable where id=:cart_id")
    suspend fun delete(cart_id: Int)

    @Query("Update CartTable set qty =:qty , price_total =:priceTotal where id=:cart_id")
    suspend fun updateQty(qty: Int, cart_id: Int,priceTotal:Int)

    @Query("Update CartTable set qty =:qty where id_product=:product_id")
    suspend fun updateQtyFromHomepage(qty: Int, product_id: String)


    @Query("SELECT SUM(price_total) FROM CartTable")
    fun countPriceTotal():  Flow<Int>


}