package com.manoj.rnm.universe.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CharacterEntity)

    @Query("SELECT * FROM character WHERE id=:id")
    suspend fun getById(id: Int): CharacterEntity?

    @Query("SELECT * FROM character")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM character")
    suspend fun clearAll()
}