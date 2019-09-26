package com.example.tennistrackerpro.activities.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tennistrackerpro.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newMatchBtn.setOnClickListener {
            val intent = Intent(this, AddMatch::class.java)
            startActivity(intent)
        }

        matchHistoryBtn.setOnClickListener {
            val intent = Intent(this, MatchHistory::class.java)
            startActivity(intent)
        }

        statisticsBtn.setOnClickListener {
            val intent = Intent(this, Statistics::class.java)
            startActivity(intent)
        }
    }
}
