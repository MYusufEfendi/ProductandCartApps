package com.yusuf.app.testtwiscode.ui.cart

import com.yusuf.app.testtwiscode.db.CartDao
import com.yusuf.app.testtwiscode.model.CartTable
import com.yusuf.app.testtwiscode.model.TransactionWithProduct
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {

    suspend fun insertCart(products: CartTable) {
        return cartDao.insert(products)
    }

//    suspend fun countCart():Int{
//        return cartDao.countBalance()
//    }

    suspend fun getQty(id_product: String): Int {
        return cartDao.getQtyProduct(id_product)
    }

    suspend fun getListCart(): List<TransactionWithProduct> {
        return cartDao.getListCart()
    }

    suspend fun delete(cart_id: Int) {
        return cartDao.delete(cart_id)
    }

    suspend fun update(qty: Int, cart_id: Int,priceTotal:Int) {
        return cartDao.updateQty(qty, cart_id,priceTotal)
    }

    suspend fun updateFromHomepage(qty: Int, product_id: String) {
        return cartDao.updateQtyFromHomepage(qty, product_id)
    }

    fun countPriceTotal(): Flow<Int> {
        return cartDao.countPriceTotal()
    }


}