package com.yusuf.app.testtwiscode.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "CartTable")
data class CartTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val id_product: String,
    val qty:Int,
    val price_total:Int
){
    companion object {
        fun ProductToCart(
            data: ProductTable,
            qty:Int
        )=CartTable(
            id_product = data.id,
            qty = qty,
            price_total =  data.price*qty
        )
    }
}

data class TransactionWithProduct(
    @Embedded
    val cart:CartTable,
    @Relation(parentColumn = "id_product",entityColumn = "id")
    val product:ProductTable
)


