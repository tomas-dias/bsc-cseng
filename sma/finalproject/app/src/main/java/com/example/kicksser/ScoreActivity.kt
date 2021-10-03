package com.example.kicksser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity()
{

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val user = FirebaseAuth.getInstance().currentUser


        if (user != null) {
            tv_name.text = user.displayName
        }

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tv_score.text = "Score: $correctAnswer/$totalQuestions"

        sendScore(correctAnswer, user)

        findViewById<Button>(R.id.button_back_to_main_score).setOnClickListener {
            openMainActivity()
        }
    }

    private fun sendScore(correctAnswer: Int, user: FirebaseUser?)
    {
        val score = hashMapOf(
            "email" to user!!.email,
            "username" to user.displayName,
            "score" to correctAnswer,
            "date" to java.util.Calendar.getInstance().time
        )

        val db = Firebase.firestore

        val id: String = db.collection("collection-name").document().id

        db.collection("score").document(id)
            .set(score)

    }

    private fun openMainActivity()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}