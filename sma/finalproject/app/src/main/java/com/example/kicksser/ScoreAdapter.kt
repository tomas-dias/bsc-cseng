package com.example.kicksser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScoreAdapter(private val scoresList : ArrayList<Score>) : RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ScoreViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)

        return ScoreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {

        val score : Score = scoresList[position]
        holder.score.text = score.score.toString()
        holder.date.text = score.date.toString()
    }

    override fun getItemCount(): Int {

        return scoresList.size
    }

    class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val score : TextView = itemView.findViewById(R.id.tv_score)
        val date : TextView = itemView.findViewById(R.id.tv_date)

    }
}