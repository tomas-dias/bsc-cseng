package com.example.kicksser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class LeaderboardActivity : AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var scoresList: ArrayList<Score>
    private lateinit var leaderboardAdapter: LeaderBoardAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        recyclerView = findViewById(R.id.rv_leaderBoard_history)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        scoresList = arrayListOf()

        leaderboardAdapter = LeaderBoardAdapter(scoresList)

        recyclerView.adapter = leaderboardAdapter

        eventChangeListener()
    }

    private fun eventChangeListener() {

        val user = FirebaseAuth.getInstance().currentUser

        if(user != null)
        {
            db = FirebaseFirestore.getInstance()
            db.collection("score")
                .orderBy("score", Query.Direction.DESCENDING)
                .orderBy("date", Query.Direction.ASCENDING)
                .limit(10)
                .get()
                .addOnSuccessListener { documents ->
                    for(document in documents)
                    {
                        scoresList.add(document.toObject(Score::class.java))
                    }

                   leaderboardAdapter.notifyDataSetChanged()
                }
        }

    }
}