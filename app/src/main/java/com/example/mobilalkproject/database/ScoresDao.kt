package com.example.mobilalkproject.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScoresDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(scores: Scores)

    @Update
    suspend fun update(scores: Scores)

    @Query("SELECT * FROM user_scores_table")
    fun getAll(): List<Scores>

}