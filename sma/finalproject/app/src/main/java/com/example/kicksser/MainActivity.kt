package com.example.kicksser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_category_one).setOnClickListener {
            openPlayersCoachesActivity()
        }

        findViewById<Button>(R.id.button_history).setOnClickListener {
            openHistoryActivity()
        }

        findViewById<Button>(R.id.button_profile).setOnClickListener {
            openProfileActivity()
        }

        findViewById<Button>(R.id.button_leaderBoard).setOnClickListener {
            openLeaderBoardActivity()
        }
    }

    private fun openPlayersCoachesActivity()
    {
        val intent = Intent(this, PlayActivity::class.java)
        startActivity(intent)
    }

    private fun openHistoryActivity()
    {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    private fun openProfileActivity()
    {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun openLeaderBoardActivity()
    {
        val intent = Intent(this, LeaderboardActivity::class.java)
        startActivity(intent)
    }
}