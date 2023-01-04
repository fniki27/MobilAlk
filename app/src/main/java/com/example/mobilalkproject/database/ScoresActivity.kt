package com.example.mobilalkproject.database

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobilalkproject.R

class ScoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_all_scores)

        val rView = findViewById<RecyclerView>(R.id.scoresRecyclerView)
        val scoreList = ScoresDatabase.getDatabase(this@ScoresActivity).scoresDao().getAll()

        rView.apply {
            layoutManager = LinearLayoutManager(this@ScoresActivity)
            adapter = ScoresAdapter().apply {
                setData(scoreList)
            }
        }

    }

}