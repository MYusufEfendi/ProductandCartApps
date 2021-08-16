package com.yusuf.app.testtwiscode.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yusuf.app.testtwiscode.model.CartTable
import com.yusuf.app.testtwiscode.model.CategoryTable
import com.yusuf.app.testtwiscode.model.ProductTable
import com.yusuf.app.testtwiscode.model.ShortBytable


@Database(
    entities = [
        ProductTable::class,
        CartTable::class,
        CategoryTable::class,
        ShortBytable::class
    ],
    exportSchema = false,
    version = 15
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun filterDao(): FilterDao
}