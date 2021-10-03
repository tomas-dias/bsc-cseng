package com.example.kicksser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class HistoryActivity : AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var scoresList: ArrayList<Score>
    private lateinit var scoreAdapter: ScoreAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        recyclerView = findViewById(R.id.rv_history)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        scoresList = arrayListOf()

        scoreAdapter = ScoreAdapter(scoresList)

        recyclerView.adapter = scoreAdapter

        eventChangeListener()
    }

    private fun eventChangeListener() {

        val user = FirebaseAuth.getInstance().currentUser

        if(user != null)
        {
            db = FirebaseFirestore.getInstance()
            db.collection("score")
                .whereEqualTo("email", user.email)
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { documents ->
                    for(document in documents)
                    {
                        scoresList.add(document.toObject(Score::class.java))
                    }

                    scoreAdapter.notifyDataSetChanged()
                }
        }

    }
}