package com.example.mobilalkproject.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_scores_table")
data class Scores(
    @PrimaryKey(autoGenerate = true)
    var userId: Int?,

    @ColumnInfo(name = "username")
    val username: String?,

    @ColumnInfo(name = "score")
    val score: String?

)
