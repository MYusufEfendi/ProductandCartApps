package com.yusuf.app.testtwiscode.ui.cart

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.yusuf.app.testtwiscode.model.TransactionWithProduct
import com.yusuf.app.testtwiscode.utils.StringUtil
import kotlinx.coroutines.flow.Flow

class CartViewModel @ViewModelInject constructor(
    private val repository: CartRepository,
    val stringUtil: StringUtil
) : ViewModel() {

    suspend fun getListCart(): List<TransactionWithProduct> {
        return repository.getListCart()
    }

    suspend fun update(qty: Int, cart_id: Int,priceTotal:Int) {
        return repository.update(qty, cart_id,priceTotal)
    }

    suspend fun updateFromHomepage(qty: Int, product_id: String) {
        return repository.updateFromHomepage(qty, product_id)
    }

    suspend fun delete(cart_id: Int) {
        return repository.delete(cart_id)
    }

    suspend fun countPriceTotal(): Flow<Int> {
        return repository.countPriceTotal()
    }

}