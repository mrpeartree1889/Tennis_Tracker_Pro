package com.example.tennistrackerpro.activities.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tennistrackerpro.R
import com.example.tennistrackerpro.activities.Adapters.OppAdapter
import com.example.tennistrackerpro.activities.DBHandler
import com.example.tennistrackerpro.activities.Models.Statistics
import kotlinx.android.synthetic.main.activity_statistics.*
import org.jetbrains.anko.alert
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class Statistics : AppCompatActivity() {

    companion object { lateinit var dbHandler: DBHandler }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        dbHandler = DBHandler(this, null, null, 1)

        val mainStatistics = dbHandler.mainStatistics(this)
        populateStatistics((mainStatistics))
        viewScoreOpp()

        matchHistoryLayout.setOnClickListener() {
            val intent = Intent(this, MatchHistory::class.java)
            startActivity(intent)
        }

        newMatchLayout.setOnClickListener() {
            val intent = Intent(this, AddMatch::class.java)
            startActivity(intent)
        }
    }

    private fun populateStatistics(mainStatistics: Statistics) {
        val matchesWon = mainStatistics.matchesWon
        val matchesLost = mainStatistics.matchesLost
        val matchesPlayed = matchesWon + matchesLost
        val setsWonScore = mainStatistics.setsWon
        val setsLostScore = mainStatistics.setsLost
        val setsPlayed = setsWonScore + setsLostScore
        val gamesWonScore = mainStatistics.gamesWon
        val gamesLostScore = mainStatistics.gamesLost
        val gamesPlayed = gamesWonScore + gamesLostScore

        // MATCHES
        matchesWonTxt.text = matchesWon.toString()
        matchesLostTxt.text = matchesLost.toString()
        matchesPlayedTxt.text = matchesPlayed.toString()

        percentMatchesYou.text = ((matchesWon.toFloat() / matchesPlayed.toFloat())*100).roundToInt().toString() + "%"
        percentMatchesOpp.text = ((matchesLost.toFloat() / matchesPlayed.toFloat())*100).roundToInt().toString() + "%"

        (percentMatchesYou.layoutParams as LinearLayout.LayoutParams).weight = (matchesWon.toFloat() / matchesPlayed.toFloat())*100
        (percentMatchesOpp.layoutParams as LinearLayout.LayoutParams).weight = (matchesLost.toFloat() / matchesPlayed.toFloat())*100

        // SETS
        setsWon.text = setsWonScore.toString()
        setsLost.text = setsLostScore.toString()
        totalSetsPlayed.text = setsPlayed.toString()

        percentSetsYou.text = ((setsWonScore.toFloat() / setsPlayed.toFloat())*100).roundToInt().toString() + "%"
        percentSetsOpp.text = ((setsLostScore.toFloat() / setsPlayed.toFloat())*100).roundToInt().toString() + "%"

        (percentSetsYou.layoutParams as LinearLayout.LayoutParams).weight = (setsWonScore.toFloat() / setsPlayed.toFloat())*100
        (percentSetsOpp.layoutParams as LinearLayout.LayoutParams).weight = (setsLostScore.toFloat() / setsPlayed.toFloat())*100

        // GAMES
        gamesWon.text = gamesWonScore.toString()
        gamesLost.text = gamesLostScore.toString()
        totalGamesPlayed.text = gamesPlayed.toString()

        percentGamesYou.text = ((gamesWonScore.toFloat() / gamesPlayed.toFloat())*100).roundToInt().toString() + "%"
        percentGamesOpp.text = ((gamesLostScore.toFloat() / gamesPlayed.toFloat())*100).roundToInt().toString() + "%"

        (percentGamesYou.layoutParams as LinearLayout.LayoutParams).weight = (gamesWonScore.toFloat() / gamesPlayed.toFloat())*100
        (percentGamesOpp.layoutParams as LinearLayout.LayoutParams).weight = (gamesLostScore.toFloat() / gamesPlayed.toFloat())*100

        // RATIOS
        val df = DecimalFormat("0.00")
        df.roundingMode = RoundingMode.CEILING

        matchRatio.text = df.format(matchesWon.toFloat() / matchesLost.toFloat()).toString()
        setRatio.text = df.format(setsWonScore.toFloat() / setsLostScore.toFloat()).toString()
        gamesRatio.text = df.format(gamesWonScore.toFloat() / gamesLostScore.toFloat()).toString()
    }

    private fun viewScoreOpp(){
        val scoreOppList = dbHandler.getScoresOpp(this)
        val adapter = OppAdapter(this, scoreOppList, this)
        val rv : RecyclerView = findViewById(R.id.rvOpponents)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        rv.adapter = adapter
    }

    fun ratioInfoBtnClicked(view: View) {
        alert {
            title = "Ratios"
            message = "Ratios represent a win vs loss value. A match ratio of 1.2 means that you won 1.2 games for every game you lost. Generally, a ratio higher than 1 means you won more than you lost."
            positiveButton("Ok!") {}
        }.show()
    }
}
