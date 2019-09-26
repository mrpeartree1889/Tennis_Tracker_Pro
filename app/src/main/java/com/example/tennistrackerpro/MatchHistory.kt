package com.example.tennistrackerpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_match.*
import kotlinx.android.synthetic.main.activity_match_history.*

class MatchHistory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)

        statisticsLayout.setOnClickListener() {
            val intent = Intent(this, Statistics::class.java)
            startActivity(intent)
        }

        newMatchLayout.setOnClickListener() {
            val intent = Intent(this, AddMatch::class.java)
            startActivity(intent)
        }
    }
}
