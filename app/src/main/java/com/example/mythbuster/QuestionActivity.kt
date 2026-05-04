package com.example.mythbuster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    private val TAG = "QuestionActivity"

    // keeping track of where we are in the list
    private var currentIndex = 0
    private var score = 0
    private var answered = false

    // all the questions stored here
    private val questions = listOf(
        Question("Putting your phone in rice fixes water damage.", false,
            "Rice doesn't actually draw out moisture effectively — silica gel or air drying works better."),
        Question("Chewing gum improves memory and concentration.", true,
            "Studies suggest chewing increases blood flow to the brain slightly."),
        Question("You only use 10% of your brain.", false,
            "This is a myth — brain scans show we use virtually all parts of our brain."),
        Question("Drinking coffee before a nap makes you wake up more refreshed.", true,
            "Caffeine takes about 20 minutes to kick in, so a short nap timed right actually works."),
        Question("Swimming right after eating causes dangerous cramps.", false,
            "Mild discomfort is possible but there's no real danger — this is an old exaggerated myth."),
        Question("Keeping bread in the fridge makes it go stale faster.", true,
            "Cold temperatures speed up starch retrogradation, which stales bread quicker."),
        Question("Lightning never strikes the same place twice.", false,
            "Lightning absolutely can and does strike the same place multiple times."),
        Question("A wooden spoon over a boiling pot stops it overflowing.", true,
            "The spoon breaks the surface tension of bubbles, slowing the boil-over."),
        Question("Humans have only five senses.", false,
            "We actually have more, including balance, temperature sensation, and pain reception."),
        Question("Rubbing butter on a burn helps it heal faster.", false,
            "Butter traps heat and can cause infection. Cool running water is the correct treatment.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        Log.d(TAG, "QuestionActivity started, total questions: ${questions.size}")

        loadQuestion()

        val btnHack = findViewById<Button>(R.id.btnHack)
        val btnMyth = findViewById<Button>(R.id.btnMyth)
        val btnNext = findViewById<Button>(R.id.btnNext)

        // user picks "Hack" (True)
        btnHack.setOnClickListener {
            if (!answered) {
                checkAnswer(true)
            }
        }

        // user picks "Myth" (False)
        btnMyth.setOnClickListener {
            if (!answered) {
                checkAnswer(false)
            }
        }

        // move to next question
        btnNext.setOnClickListener {
            if (!answered) {
                Toast.makeText(this, "Please answer before moving on!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            currentIndex++
            answered = false

            if (currentIndex < questions.size) {
                // still more questions left
                Log.d(TAG, "Moving to question index: $currentIndex")
                loadQuestion()
            } else {
                // done with all questions - go to score screen
                Log.d(TAG, "All questions done. Score: $score")
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    // loads the current question onto the screen
    private fun loadQuestion() {
        val tvStatement = findViewById<TextView>(R.id.tvStatement)
        val tvFeedback = findViewById<TextView>(R.id.tvFeedback)
        val tvProgress = findViewById<TextView>(R.id.tvProgress)

        tvStatement.text = questions[currentIndex].statement
        tvFeedback.text = ""
        tvProgress.text = "Question ${currentIndex + 1} of ${questions.size}"

        Log.d(TAG, "Loaded question: ${questions[currentIndex].statement}")
    }

    // checks if the user got it right and shows feedback
    private fun checkAnswer(userAnswer: Boolean) {
        answered = true
        val tvFeedback = findViewById<TextView>(R.id.tvFeedback)
        val correct = questions[currentIndex].isHack

        if (userAnswer == correct) {
            score++
            tvFeedback.text = "✅ Correct! That's a real time saver!\n\n${questions[currentIndex].explanation}"
            tvFeedback.setTextColor(resources.getColor(android.R.color.holo_green_light, null))
            Log.d(TAG, "Correct answer at index $currentIndex")
        } else {
            tvFeedback.text = "❌ Wrong! That's just an urban myth.\n\n${questions[currentIndex].explanation}"
            tvFeedback.setTextColor(resources.getColor(android.R.color.holo_red_light, null))
            Log.d(TAG, "Wrong answer at index $currentIndex")
        }
    }
}
