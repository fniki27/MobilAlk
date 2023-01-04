package com.example.mobilalkproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Scores::class], version = 1)
abstract class ScoresDatabase : RoomDatabase() {

    abstract fun scoresDao(): ScoresDao

    companion object {

        @Volatile
        private var INSTANCE: ScoresDatabase? = null

        fun getDatabase(context: Context): ScoresDatabase {

            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScoresDatabase::class.java,
                    "scores_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}