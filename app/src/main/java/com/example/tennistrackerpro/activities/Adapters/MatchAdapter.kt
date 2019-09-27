package com.example.tennistrackerpro.activities.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.tennistrackerpro.R
import com.example.tennistrackerpro.activities.Activities.AddMatch
import com.example.tennistrackerpro.activities.Activities.MatchHistory
import com.example.tennistrackerpro.activities.DBHandler
import com.example.tennistrackerpro.activities.Models.Match
import kotlinx.android.synthetic.main.activity_add_match.view.*
import kotlinx.android.synthetic.main.lo_match.view.*
import kotlinx.android.synthetic.main.lo_match.view.courtName
import org.jetbrains.anko.*
import kotlin.collections.ArrayList

class MatchAdapter(val mCtx: Context, val matches : ArrayList<Match>, val activity: MatchHistory) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    companion object {
        lateinit var dbHandler: DBHandler
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val matchId = itemView.matchIdText
        val matchDate = itemView.matchDate
        val matchCourt = itemView.courtName
        val opponentName = itemView.matchOpponent
        val myScore = itemView.myScoreMatchLo
        val opponentScore = itemView.oppScoreMatchLo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_match,parent,false)

        dbHandler =
            DBHandler(mCtx, null, null, 1)

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

        if (holder.matchCourt.text.isEmpty()) {
            holder.matchCourt.visibility = View.GONE
        }

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

        // ON CLICK FOR DOWN ARROW
        holder.itemView.downButton.setOnClickListener() {
            populateSetScore(match, holder)
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

        // ON CLICK DELETE BUTTON
        holder.itemView.deleteMatchBtn.setOnClickListener() {
            mCtx.alert {
                title = "Are you sure you want to delete this match?"
                message = "If you delete this match, it will be permanently erased and you won't be able to retrieve it."
                positiveButton("Proceed") {
                    matches.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeRemoved(position, matches.size)
                    dbHandler.deleteMatch(mCtx, match)
                }
                negativeButton ("Cancel") {}
            }.show()
        }
    }

    private fun configureLayoutBottom(holder: ViewHolder) {
        val editBtn = holder.itemView.editMatchBtn
        val deleteBtn = holder.itemView.deleteMatchBtn
        deleteBtn.id = R.id.deleteMatchBtn
        editBtn.id = R.id.editMatchBtn

        val myLayout = holder.itemView.matchBottomLayout
        val set = ConstraintSet()

        set.constrainWidth(deleteBtn.id, 0)
        set.constrainWidth(editBtn.id, 0)
        set.constrainHeight(deleteBtn.id, 100)
        set.constrainHeight(editBtn.id, 100)

        set.connect(editBtn.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        set.connect(editBtn.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 8)
        set.connect(editBtn.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
        set.connect(editBtn.id, ConstraintSet.END, deleteBtn.id, ConstraintSet.START, 8)

        set.connect(deleteBtn.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        set.connect(deleteBtn.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 8)

        set.applyTo(myLayout)
    }

    private fun populateSetScore(match: Match, holder: ViewHolder) {
        // SETS AND POPULATE
        if(match.firstSetMyScore == 0 && match.firstSetOpponentScore == 0){
            configureLayoutBottom(holder)
            holder.itemView.tableLayout.visibility = View.GONE
            mCtx.toast("No sets were recorded for this match")
        } else if (match.firstSetMyScore != 0 || match.firstSetOpponentScore != 0) {
            holder.itemView.myScoreSet1.text = match.firstSetMyScore.toString()
            holder.itemView.oppScoreSet1.text = match.firstSetOpponentScore.toString()

            if(match.firstSetMyScore > match.firstSetOpponentScore) {
                holder.itemView.myScoreSet1.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.firstSetMyScore < match.firstSetOpponentScore) {
                holder.itemView.oppScoreSet1.setBackgroundResource(R.color.primaryColorLight)
            }
        }

        if(match.secondSetMyScore == 0 && match.secondSetOpponentScore == 0){
            holder.itemView.rowSet2.visibility = View.GONE
        } else if (match.secondSetMyScore != 0 || match.secondSetOpponentScore != 0) {
            holder.itemView.myScoreSet2.text = match.secondSetMyScore.toString()
            holder.itemView.oppScoreSet2.text = match.secondSetOpponentScore.toString()

            if(match.secondSetMyScore > match.secondSetOpponentScore) {
                holder.itemView.myScoreSet2.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.secondSetMyScore < match.secondSetOpponentScore) {
                holder.itemView.oppScoreSet2.setBackgroundResource(R.color.primaryColorLight)
            }
        }

        if(match.thirdSetMyScore == 0 && match.thirdSetOpponentScore == 0){
            holder.itemView.rowSet3.visibility = View.GONE
        } else if (match.thirdSetMyScore != 0 || match.thirdSetOpponentScore != 0) {
            holder.itemView.myScoreSet3.text = match.thirdSetMyScore.toString()
            holder.itemView.oppScoreSet3.text = match.thirdSetOpponentScore.toString()

            if(match.thirdSetMyScore > match.thirdSetOpponentScore) {
                holder.itemView.myScoreSet3.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.thirdSetMyScore < match.thirdSetOpponentScore) {
                holder.itemView.oppScoreSet3.setBackgroundResource(R.color.primaryColorLight)
            }
        }

        if(match.fourthSetMyScore == 0 && match.fourthSetOpponentScore == 0){
            holder.itemView.rowSet4.visibility = View.GONE
        } else if (match.fourthSetMyScore != 0 || match.fourthSetOpponentScore != 0) {
            holder.itemView.myScoreSet4.text = match.fourthSetMyScore.toString()
            holder.itemView.oppScoreSet4.text = match.fourthSetOpponentScore.toString()

            if(match.fourthSetMyScore > match.fourthSetOpponentScore) {
                holder.itemView.myScoreSet4.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.fourthSetMyScore < match.fourthSetOpponentScore) {
                holder.itemView.oppScoreSet4.setBackgroundResource(R.color.primaryColorLight)
            }
        }

        if(match.fifthSetMyScore == 0 && match.fifthSetOpponentScore == 0){
            holder.itemView.rowSet5.visibility = View.GONE
        } else if (match.fifthSetMyScore != 0 || match.fifthSetOpponentScore != 0) {
            holder.itemView.myScoreSet5.text = match.fifthSetMyScore.toString()
            holder.itemView.oppScoreSet5.text = match.fifthSetOpponentScore.toString()

            if(match.fifthSetMyScore > match.fifthSetOpponentScore) {
                holder.itemView.myScoreSet5.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.fifthSetMyScore < match.fifthSetOpponentScore) {
                holder.itemView.oppScoreSet5.setBackgroundResource(R.color.primaryColorLight)
            }
        }

        if(match.sixthSetMyScore == 0 && match.sixthSetOpponentScore == 0){
            holder.itemView.rowSet6.visibility = View.GONE
        } else if (match.sixthSetMyScore != 0 || match.sixthSetOpponentScore != 0) {
            holder.itemView.myScoreSet6.text = match.sixthSetMyScore.toString()
            holder.itemView.oppScoreSet6.text = match.sixthSetOpponentScore.toString()

            if(match.sixthSetMyScore > match.sixthSetOpponentScore) {
                holder.itemView.myScoreSet6.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.sixthSetMyScore < match.sixthSetOpponentScore) {
                holder.itemView.oppScoreSet6.setBackgroundResource(R.color.primaryColorLight)
            }
        }

        if(match.seventhSetMyScore == 0 && match.seventhSetOpponentScore == 0){
            holder.itemView.rowSet7.visibility = View.GONE
        } else if (match.seventhSetMyScore != 0 || match.seventhSetOpponentScore != 0) {
            holder.itemView.myScoreSet7.text = match.seventhSetMyScore.toString()
            holder.itemView.oppScoreSet7.text = match.seventhSetOpponentScore.toString()

            if(match.seventhSetMyScore > match.seventhSetOpponentScore) {
                holder.itemView.myScoreSet7.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.seventhSetMyScore < match.seventhSetOpponentScore) {
                holder.itemView.oppScoreSet7.setBackgroundResource(R.color.primaryColorLight)
            }
        }

        if(match.eighthSetMyScore == 0 && match.eighthSetOpponentScore == 0){
            holder.itemView.rowSet8.visibility = View.GONE
        } else if (match.eighthSetMyScore != 0 || match.eighthSetOpponentScore != 0) {
            holder.itemView.myScoreSet8.text = match.eighthSetMyScore.toString()
            holder.itemView.oppScoreSet8.text = match.eighthSetOpponentScore.toString()

            if(match.eighthSetMyScore > match.eighthSetOpponentScore) {
                holder.itemView.myScoreSet8.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.eighthSetMyScore < match.eighthSetOpponentScore) {
                holder.itemView.oppScoreSet8.setBackgroundResource(R.color.primaryColorLight)
            }
        }

        if(match.ninthSetMyScore == 0 && match.ninthSetOpponentScore == 0){
            holder.itemView.rowSet9.visibility = View.GONE
        } else if (match.ninthSetMyScore != 0 || match.ninthSetOpponentScore != 0) {
            holder.itemView.myScoreSet9.text = match.ninthSetMyScore.toString()
            holder.itemView.oppScoreSet9.text = match.ninthSetOpponentScore.toString()

            if(match.ninthSetMyScore > match.ninthSetOpponentScore) {
                holder.itemView.myScoreSet9.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.ninthSetMyScore < match.ninthSetOpponentScore) {
                holder.itemView.oppScoreSet9.setBackgroundResource(R.color.primaryColorLight)
            }
        }

        if(match.tenthSetMyScore == 0 && match.tenthSetOpponentScore == 0){
            holder.itemView.rowSet10.visibility = View.GONE
        } else if (match.tenthSetMyScore != 0 || match.tenthSetOpponentScore != 0) {
            holder.itemView.myScoreSet10.text = match.tenthSetMyScore.toString()
            holder.itemView.oppScoreSet10.text = match.tenthSetOpponentScore.toString()

            if(match.tenthSetMyScore > match.tenthSetOpponentScore) {
                holder.itemView.myScoreSet10.setBackgroundResource(R.color.primaryColorLight)
            } else if (match.tenthSetMyScore < match.tenthSetOpponentScore) {
                holder.itemView.oppScoreSet10.setBackgroundResource(R.color.primaryColorLight)
            }
        }
    }


}