package com.example.tennistrackerpro.activities.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tennistrackerpro.R
import com.example.tennistrackerpro.activities.Activities.AddMatch
import com.example.tennistrackerpro.activities.Activities.MatchHistory
import com.example.tennistrackerpro.activities.Models.Match
import kotlinx.android.synthetic.main.lo_match.view.*
import kotlin.collections.ArrayList

class MatchAdapter(val mCtx: Context, val matches : ArrayList<Match>, val activity: MatchHistory) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val matchId = itemView.matchIdText
        val matchDate = itemView.matchDate
        val matchCourt = itemView.courtName
        val opponentName = itemView.matchOpponent
        val myScore = itemView.myScoreMatchLo
        val opponentScore = itemView.oppScoreMatchLo
        val mySetScore1 = itemView.myScoreSet1
        val oppSetScore1 = itemView.oppScoreSet1
        val mySetScore2= itemView.myScoreSet2
        val oppSetScore2 = itemView.oppScoreSet2
        val mySetScore3 = itemView.myScoreSet3
        val oppSetScore3 = itemView.oppScoreSet3
        val mySetScore4 = itemView.myScoreSet4
        val oppSetScore4 = itemView.oppScoreSet4
        val mySetScore5 = itemView.myScoreSet5
        val oppSetScore5 = itemView.oppScoreSet5
        val mySetScore6 = itemView.myScoreSet6
        val oppSetScore6 = itemView.oppScoreSet6
        val mySetScore7 = itemView.myScoreSet7
        val oppSetScore7 = itemView.oppScoreSet7
        val mySetScore8 = itemView.myScoreSet8
        val oppSetScore8 = itemView.oppScoreSet8
        val mySetScore9 = itemView.myScoreSet9
        val oppSetScore9 = itemView.oppScoreSet9
        val mySetScore10 = itemView.myScoreSet10
        val oppSetScore10 = itemView.oppScoreSet10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_match,parent,false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // POPULATE VIEWHOLDER
        val match : Match = matches[position]
        holder.matchId.text = (position + 1).toString()
        holder.matchDate.text = match.date
        holder.opponentName.text = match.opponentName
        holder.matchCourt.text = match.courtName
        holder.myScore.text = match.myScore.toString()
        holder.opponentScore.text = match.opponentScore.toString()

        // EDIT COLOR ON MATCH SCORE
        when {
            match.myScore > match.opponentScore -> {
                holder.myScore.setBackgroundResource(R.drawable.green_bckg_bottom_radius)
                holder.opponentScore.setBackgroundResource(R.drawable.green_bckg_bottom_radius)
            }
            match.myScore == match.opponentScore -> {
                holder.myScore.setBackgroundResource(R.drawable.yellow_bckg_bottom_radius)
                holder.opponentScore.setBackgroundResource(R.drawable.yellow_bckg_bottom_radius)
            }
            match.myScore < match.opponentScore -> {
                holder.myScore.setBackgroundResource(R.drawable.red_bckg_bottom_radius)
                holder.opponentScore.setBackgroundResource(R.drawable.red_bckg_bottom_radius)
            }
        }

        // SETS AND POPULATE
        if(match.firstSetMyScore == 0 && match.firstSetOpponentScore == 0){
            holder.itemView.tableLayout.visibility = View.GONE
        } else if (match.firstSetMyScore != 0 || match.firstSetOpponentScore != 0) {
            holder.itemView.myScoreSet1.text = match.firstSetMyScore.toString()
            holder.itemView.oppScoreSet1.text = match.firstSetOpponentScore.toString()
        }

        if(match.secondSetMyScore == 0 && match.secondSetOpponentScore == 0){
            holder.itemView.rowSet2.visibility = View.GONE
        } else if (match.secondSetMyScore != 0 || match.secondSetOpponentScore != 0) {
            holder.itemView.myScoreSet2.text = match.secondSetMyScore.toString()
            holder.itemView.oppScoreSet2.text = match.secondSetOpponentScore.toString()
        }

        if(match.thirdSetMyScore == 0 && match.thirdSetOpponentScore == 0){
            holder.itemView.rowSet3.visibility = View.GONE
        } else if (match.thirdSetMyScore != 0 || match.thirdSetOpponentScore != 0) {
            holder.itemView.myScoreSet3.text = match.thirdSetMyScore.toString()
            holder.itemView.oppScoreSet3.text = match.thirdSetOpponentScore.toString()
        }

        if(match.fourthSetMyScore == 0 && match.fourthSetOpponentScore == 0){
            holder.itemView.rowSet4.visibility = View.GONE
        } else if (match.fourthSetMyScore != 0 || match.fourthSetOpponentScore != 0) {
            holder.itemView.myScoreSet4.text = match.fourthSetMyScore.toString()
            holder.itemView.oppScoreSet4.text = match.fourthSetOpponentScore.toString()
        }

        if(match.fifthSetMyScore == 0 && match.fifthSetOpponentScore == 0){
            holder.itemView.rowSet5.visibility = View.GONE
        } else if (match.fifthSetMyScore != 0 || match.fifthSetOpponentScore != 0) {
            holder.itemView.myScoreSet5.text = match.fifthSetMyScore.toString()
            holder.itemView.oppScoreSet5.text = match.fifthSetOpponentScore.toString()
        }

        if(match.sixthSetMyScore == 0 && match.sixthSetOpponentScore == 0){
            holder.itemView.rowSet6.visibility = View.GONE
        } else if (match.sixthSetMyScore != 0 || match.sixthSetOpponentScore != 0) {
            holder.itemView.myScoreSet6.text = match.sixthSetMyScore.toString()
            holder.itemView.oppScoreSet6.text = match.sixthSetOpponentScore.toString()
        }

        if(match.seventhSetMyScore == 0 && match.seventhSetOpponentScore == 0){
            holder.itemView.rowSet7.visibility = View.GONE
        } else if (match.seventhSetMyScore != 0 || match.seventhSetOpponentScore != 0) {
            holder.itemView.myScoreSet7.text = match.seventhSetMyScore.toString()
            holder.itemView.oppScoreSet7.text = match.seventhSetOpponentScore.toString()
        }

        if(match.eighthSetMyScore == 0 && match.eighthSetOpponentScore == 0){
            holder.itemView.rowSet8.visibility = View.GONE
        } else if (match.eighthSetMyScore != 0 || match.eighthSetOpponentScore != 0) {
            holder.itemView.myScoreSet8.text = match.eighthSetMyScore.toString()
            holder.itemView.oppScoreSet8.text = match.eighthSetOpponentScore.toString()
        }

        if(match.ninthSetMyScore == 0 && match.ninthSetOpponentScore == 0){
            holder.itemView.rowSet9.visibility = View.GONE
        } else if (match.ninthSetMyScore != 0 || match.ninthSetOpponentScore != 0) {
            holder.itemView.myScoreSet9.text = match.ninthSetMyScore.toString()
            holder.itemView.oppScoreSet9.text = match.ninthSetOpponentScore.toString()
        }

        if(match.tenthSetMyScore == 0 && match.tenthSetOpponentScore == 0){
            holder.itemView.rowSet10.visibility = View.GONE
        } else if (match.tenthSetMyScore != 0 || match.tenthSetOpponentScore != 0) {
            holder.itemView.myScoreSet10.text = match.tenthSetMyScore.toString()
            holder.itemView.oppScoreSet10.text = match.tenthSetOpponentScore.toString()
        }




        // ON CLICK FOR DOWN ARROW
        holder.itemView.downButton.setOnClickListener() {
            holder.itemView.matchBottomLayout.visibility = View.VISIBLE
            holder.itemView.downButton.visibility = View.GONE
            holder.itemView.upButton.visibility = View.VISIBLE
        }

        // ON CLICK FOR UP ARROW
        holder.itemView.upButton.setOnClickListener() {
            holder.itemView.matchBottomLayout.visibility = View.GONE
            holder.itemView.downButton.visibility = View.VISIBLE
            holder.itemView.upButton.visibility = View.GONE
        }

        // ON CLICK FOR EDIT BUTTON
        holder.itemView.editMatchBtn.setOnClickListener() {
            val matchIndex: Int = match.MatchID
            val intent = Intent(mCtx, AddMatch::class.java)
            intent.putExtra("MATCH_ID", matchIndex)
            intent.putExtra("MATCH_POSITION", position)

            activity.startActivity(intent)
        }
    }
}