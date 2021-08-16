package com.yusuf.app.testtwiscode.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CategoryTable")
data class CategoryTable(
        @PrimaryKey
        val id_cat:String,
        val cat_name:String,
        val is_selected:Boolean = false
){
        companion object{
                fun respToCategorytable(data: ProductResponse
                ) = CategoryTable(
                        data.category.cat_id,
                        data.category.cat_name,
                        false,
                )
        }
}

@Entity(tableName = "ShortByTable")
data class ShortBytable(
        @PrimaryKey
        val id:Int,
        val name:String,
        val is_selected:Boolean = false
)
