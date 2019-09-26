package com.example.tennistrackerpro.activities.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tennistrackerpro.R
import com.example.tennistrackerpro.activities.Adapters.MatchAdapter
import com.example.tennistrackerpro.activities.DBHandler
import kotlinx.android.synthetic.main.activity_match_history.*

class MatchHistory : AppCompatActivity() {

    companion object { lateinit var dbHandler: DBHandler }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)

        dbHandler =
            DBHandler(this, null, null, 1)

        viewMatches()

        optBtn.setOnClickListener() {
            exportDataBtn.visibility = View.VISIBLE
        }

        optBtnClicked.setOnClickListener() {
            exportDataBtn.visibility = View.GONE
        }

        statisticsLayout.setOnClickListener() {
            val intent = Intent(this, Statistics::class.java)
            startActivity(intent)
        }

        newMatchLayout.setOnClickListener() {
            val intent = Intent(this, AddMatch::class.java)
            startActivity(intent)
        }
    }

    private fun viewMatches(){
        val matchList = dbHandler.getMatches(this)
        val adapter = MatchAdapter(this, matchList, this)
        val rv : RecyclerView = findViewById(R.id.matchHistoryList)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        rv.adapter = adapter
    }

    override fun onResume() {
        viewMatches()
        super.onResume()
    }
}
