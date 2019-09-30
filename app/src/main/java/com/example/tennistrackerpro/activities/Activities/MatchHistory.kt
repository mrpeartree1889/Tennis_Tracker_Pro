package com.example.tennistrackerpro.activities.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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

        populateSpinner()

        statisticsLayout.setOnClickListener() {
            val intent = Intent(this, Statistics::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        newMatchLayout.setOnClickListener() {
            val intent = Intent(this, AddMatch::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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

    private fun populateSpinner () {
        val spinner = findViewById<Spinner>(R.id.spinnerOpponentName)
        val names = dbHandler.getUniqueOppNames(this)
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, names)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {   }

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                }
            }
        }
    }
}
