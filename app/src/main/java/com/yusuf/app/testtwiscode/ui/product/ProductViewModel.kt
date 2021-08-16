package com.yusuf.app.testtwiscode.ui.product

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.app.testtwiscode.model.CategoryTable
import com.yusuf.app.testtwiscode.model.ProductTable
import com.yusuf.app.testtwiscode.model.ShortBytable
import com.yusuf.app.testtwiscode.ui.cart.CartRepository
import com.yusuf.app.testtwiscode.utils.StringUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber


class ProductViewModel @ViewModelInject constructor(
    private val repository: ProductRepository,
    val cartRepository: CartRepository,
    private val stringUtil: StringUtil
) : ViewModel() {

    val Loading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage by lazy { MutableLiveData<String>("") }
    var letsLoad:Boolean = true

    fun loadProduct(categoryId:String = "",shortBy:String ): Flow<List<ProductTable>> {
        if (letsLoad)
        fetchProduct()
        return repository.getProduct(categoryId,shortBy)
    }

    fun fetchProduct() = viewModelScope.launch {
        try {
            Loading.value = true
            val response = repository.fetchProduct()
            if (response.isSuccessful) {
                Timber.e("fetchProduct ${response.body()}")
                response.body()!!.forEach {
                    repository.insertProduct(ProductTable.ResponseToTable(it, stringUtil))
                    repository.insertCategory(CategoryTable.respToCategorytable(it))
                }
                fetchShortBy()
                Timber.e("count insert ${repository.countProduct()}")
                Loading.value = false
            } else {
                Loading.value = false
            }
        } catch (e: Exception) {
            Timber.e("error catch $e")
            errorMessage.postValue(e.message)
            Loading.value = false
        }
    }

    suspend fun getCategory():List<CategoryTable>{
        return repository.getCategory()
    }

    suspend fun getShortBy():List<ShortBytable>{
        return repository.getShortBy()
    }
    fun fetchShortBy(){
        viewModelScope.launch{
            var shortby = ArrayList<ShortBytable>()
            shortby.add(
                ShortBytable(
                    id= 1,
                    name = "Termurah",
                    is_selected = false,
                ),
            )
            shortby.add(
                ShortBytable(
                    id= 2,
                    name = "Termahal",
                    is_selected = false,
                )
            )
            shortby.forEach {
                repository.insertShortBy(it)
            }
        }

    }
}
