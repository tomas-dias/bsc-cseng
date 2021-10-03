package com.example.kicksser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LeaderBoardAdapter(private val scoresList : ArrayList<Score>) : RecyclerView.Adapter<LeaderBoardAdapter.LeaderboardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): LeaderboardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard, parent, false)

        return LeaderboardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {

        val score : Score = scoresList[position]
        holder.name.text = score.username
        holder.score.text = score.score.toString()
        holder.date.text = score.date.toString()
    }

    override fun getItemCount(): Int {

        return scoresList.size
    }

    class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.tv_leaderboard_name)
        val score : TextView = itemView.findViewById(R.id.tv_leaderboard_score)
        val date : TextView = itemView.findViewById(R.id.tv_leaderboard_date)

    }
}