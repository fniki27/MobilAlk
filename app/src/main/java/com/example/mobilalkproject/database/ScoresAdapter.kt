package com.example.mobilalkproject.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilalkproject.R

class ScoresAdapter: RecyclerView.Adapter<ScoresAdapter.ScoresViewHolder>() {

    private val list = mutableListOf<Scores>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scores, parent, false)
        return ScoresViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoresViewHolder, position: Int) {
        val score = list[position]
        holder.usernameScore.text = score.username
        holder.score.text = score.score
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(data: List<Scores>) {
        list.apply {
            clear()
            addAll(data)
        }
    }

    class ScoresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val usernameScore: TextView = itemView.findViewById(R.id.username_score)
        val score: TextView = itemView.findViewById(R.id.score)
    }
}