package com.example.mythbuster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // tag for logging - just makes it easier to find my logs
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Welcome screen loaded")

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val startButton = findViewById<Button>(R.id.btnStart)

        welcomeText.text = "Welcome to Life Hack or Urban Myth!\nTest your common sense and see if you can spot the real tips from the nonsense."

        startButton.setOnClickListener {
            Log.d(TAG, "Start button clicked")
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }
}
