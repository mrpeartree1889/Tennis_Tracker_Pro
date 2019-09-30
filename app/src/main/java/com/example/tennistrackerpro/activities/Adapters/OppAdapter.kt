package com.example.tennistrackerpro.activities.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tennistrackerpro.R
import com.example.tennistrackerpro.activities.Activities.Statistics
import com.example.tennistrackerpro.activities.DBHandler
import com.example.tennistrackerpro.activities.Models.scoreOpponents
import kotlinx.android.synthetic.main.lo_match.view.*
import kotlinx.android.synthetic.main.lo_opponents.view.*
import org.jetbrains.anko.alert
import java.math.RoundingMode
import java.text.DecimalFormat

class OppAdapter(val mCtx: Context, val scoreOpponents : ArrayList<scoreOpponents>, val activity: Statistics) : RecyclerView.Adapter<OppAdapter.ViewHolder>() {

    companion object {
        lateinit var dbHandler: DBHandler
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val oppName = itemView.oppName
        val matchesWon = itemView.loWon
        val matchesLost = itemView.loLost
        val oppRatio = itemView.loRatio
        val totalGames = itemView.totalGames
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_opponents,parent,false)

        dbHandler =
            DBHandler(mCtx, null, null, 1)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return scoreOpponents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scoreOpp : scoreOpponents = scoreOpponents[position]

        val df = DecimalFormat("0.00")
        df.roundingMode = RoundingMode.CEILING

        holder.oppName.text = scoreOpp.opponentName
        holder.matchesWon.text = scoreOpp.matchesWon.toString()
        holder.matchesLost.text = scoreOpp.matchesLost.toString()
        holder.totalGames.text = (scoreOpp.matchesWon + scoreOpp.matchesLost).toString()
        if (scoreOpp.matchesWon != 0 && scoreOpp.matchesLost != 0) {
            holder.oppRatio.text = df.format(scoreOpp.matchesWon.toFloat() / scoreOpp.matchesLost.toFloat()).toString()
        } else {holder.oppRatio.text = "-,--"}

    }


}