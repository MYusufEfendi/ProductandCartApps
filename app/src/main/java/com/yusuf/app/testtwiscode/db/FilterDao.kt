package com.yusuf.app.testtwiscode.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusuf.app.testtwiscode.model.CategoryTable
import com.yusuf.app.testtwiscode.model.ShortBytable

@Dao
interface FilterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryTable: CategoryTable)

    @Query("SELECT * FROM CategoryTable")
    suspend fun getAllCategory(): List<CategoryTable>

    @Query("SELECT * FROM CategoryTable where is_selected = 1")
    fun getSelectedCategory(): CategoryTable

    @Query("UPDATE CategoryTable set is_selected =:isSelected")
    fun updateStatusCategory(isSelected: Boolean)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShortBy(shortBytable: ShortBytable)

    @Query("SELECT * FROM ShortBytable")
    suspend fun getAllShortBy(): List<ShortBytable>

    @Query("SELECT * FROM ShortBytable where is_selected = 1")
    fun getSelectedShortBy(): ShortBytable

    @Query("UPDATE ShortBytable set is_selected =:isSelected")
    fun updateStatusShortBy(isSelected: Boolean)


}