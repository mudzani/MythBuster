package com.example.mythbuster

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    private val TAG = "ReviewActivity"

    private val questions = listOf(
        Question("Putting your phone in rice fixes water damage.", false,
            "Rice doesn't actually draw out moisture effectively."),
        Question("Chewing gum improves memory and concentration.", true,
            "Studies suggest chewing increases blood flow to the brain slightly."),
        Question("You only use 10% of your brain.", false,
            "Brain scans show we use virtually all parts of our brain."),
        Question("Drinking coffee before a nap makes you wake up more refreshed.", true,
            "Caffeine takes about 20 minutes to kick in — a short nap timed right works."),
        Question("Swimming right after eating causes dangerous cramps.", false,
            "Mild discomfort is possible but no real danger exists."),
        Question("Keeping bread in the fridge makes it go stale faster.", true,
            "Cold temperatures speed up starch retrogradation."),
        Question("Lightning never strikes the same place twice.", false,
            "Lightning can and does strike the same place multiple times."),
        Question("A wooden spoon over a boiling pot stops it overflowing.", true,
            "The spoon breaks the surface tension of bubbles."),
        Question("Humans have only five senses.", false,
            "We actually have more including balance and temperature sensing."),
        Question("Rubbing butter on a burn helps it heal faster.", false,
            "Butter traps heat and can cause infection. Use cool running water.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "Review screen loaded")

       
        val scrollView = ScrollView(this)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(32, 32, 32, 32)
        layout.setBackgroundColor(0xFF1A1A2E.toInt())

        for ((index, q) in questions.withIndex()) {
            val label = if (q.isHack) "✅ HACK" else "❌ MYTH"
            val tv = TextView(this)
            tv.text = "${index + 1}. ${q.statement}\n$label\n💡 ${q.explanation}\n"
            tv.textSize = 14f
            tv.setTextColor(0xFFE0E0E0.toInt())
            tv.setPadding(0, 16, 0, 16)
            layout.addView(tv)
        }

        scrollView.addView(layout)
        setContentView(scrollView)
    }
}
