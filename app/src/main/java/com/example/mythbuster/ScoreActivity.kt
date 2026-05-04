package com.example.mythbuster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    private val TAG = "ScoreActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // getting the score passed from QuestionActivity
        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 10)

        Log.d(TAG, "Score screen loaded. Score: $score / $total")

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvFeedback = findViewById<TextView>(R.id.tvScoreFeedback)
        val btnReview = findViewById<Button>(R.id.btnReview)

        tvScore.text = "You got $score out of $total correct!"

        // personalised message based on how well they did
        tvFeedback.text = when {
            score == total -> "🏆 Master Hacker! You know your stuff."
            score >= total * 0.7 -> "👏 Great job! You've got good instincts."
            score >= total * 0.4 -> "📚 Not bad, keep practising!"
            else -> "⚠️ Stay Safe Online! You might want to think twice next time."
        }

        btnReview.setOnClickListener {
            Log.d(TAG, "Review button clicked")
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }
    }
}
