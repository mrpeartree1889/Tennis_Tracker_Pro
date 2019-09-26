package com.example.tennistrackerpro.activities.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tennistrackerpro.R
import kotlinx.android.synthetic.main.activity_statistics.*

class Statistics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        matchHistoryLayout.setOnClickListener() {
            val intent = Intent(this, MatchHistory::class.java)
            startActivity(intent)
        }

        newMatchLayout.setOnClickListener() {
            val intent = Intent(this, AddMatch::class.java)
            startActivity(intent)
        }
    }
}
