package com.example.tennistrackerpro.activities.Activities

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.tennistrackerpro.R
import com.example.tennistrackerpro.activities.DBHandler
import com.example.tennistrackerpro.activities.Models.Match
import kotlinx.android.synthetic.main.activity_add_match.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddMatch : AppCompatActivity() {

    companion object { lateinit var dbHandler: DBHandler }

    // SCORING METHOD : 1 is through match overall, 2 is through set
    private var scoringMethod = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_match)

        // FIRE UP DATABASE
        dbHandler = DBHandler(this, null, null, 1)

        // POSSIBILITY OF EDIT
        var matchId = -1
        val matchIdRetrieved = intent.getIntExtra("MATCH_ID", -1)
        if (matchIdRetrieved >= 0) {matchId = matchIdRetrieved}
        val matchPosition = intent.getIntExtra("MATCH_POSITION", 0) +1

        if (matchId != -1) {
            val match: Match = dbHandler.getMatch(this, matchId)
            populateView(match, matchPosition)
        }

        // SCORING
        // MATCH SCORING BTNS
        plusBtnYou.setOnClickListener { scoreMatchPlus(myScore) }
        minusBtnYou.setOnClickListener { scoreMatchMinus(myScore) }
        plusBtnOpp.setOnClickListener { scoreMatchPlus(oppScore) }
        minusBtnOpp.setOnClickListener { scoreMatchMinus(oppScore) }

        // SET SCORING BTNS
        // SET 1
        plusBtnYouSet1.setOnClickListener { plusSetScore(scoreYouSet1) }
        minusBtnYouSet1.setOnClickListener { minusSetScore(scoreYouSet1)}
        plusBtnOppSet1.setOnClickListener {plusSetScore(scoreOppSet1)}
        minusBtnOppSet1.setOnClickListener {minusSetScore(scoreOppSet1)}
        // SET 2
        plusBtnYouSet2.setOnClickListener { plusSetScore(scoreYouSet2) }
        minusBtnYouSet2.setOnClickListener { minusSetScore(scoreYouSet2)}
        plusBtnOppSet2.setOnClickListener {plusSetScore(scoreOppSet2)}
        minusBtnOppSet2.setOnClickListener {minusSetScore(scoreOppSet2)}
        // SET 3
        plusBtnYouSet3.setOnClickListener { plusSetScore(scoreYouSet3) }
        minusBtnYouSet3.setOnClickListener { minusSetScore(scoreYouSet3)}
        plusBtnOppSet3.setOnClickListener {plusSetScore(scoreOppSet3)}
        minusBtnOppSet3.setOnClickListener {minusSetScore(scoreOppSet3)}
        // SET 4
        plusBtnYouSet4.setOnClickListener { plusSetScore(scoreYouSet4) }
        minusBtnYouSet4.setOnClickListener { minusSetScore(scoreYouSet4)}
        plusBtnOppSet4.setOnClickListener {plusSetScore(scoreOppSet4)}
        minusBtnOppSet4.setOnClickListener {minusSetScore(scoreOppSet4)}
        // SET 5
        plusBtnYouSet5.setOnClickListener { plusSetScore(scoreYouSet5) }
        minusBtnYouSet5.setOnClickListener { minusSetScore(scoreYouSet5)}
        plusBtnOppSet5.setOnClickListener {plusSetScore(scoreOppSet5)}
        minusBtnOppSet5.setOnClickListener {minusSetScore(scoreOppSet5)}
        // SET 6
        plusBtnYouSet6.setOnClickListener { plusSetScore(scoreYouSet6) }
        minusBtnYouSet6.setOnClickListener { minusSetScore(scoreYouSet6)}
        plusBtnOppSet6.setOnClickListener {plusSetScore(scoreOppSet6)}
        minusBtnOppSet6.setOnClickListener {minusSetScore(scoreOppSet6)}
        // SET 7
        plusBtnYouSet7.setOnClickListener { plusSetScore(scoreYouSet7) }
        minusBtnYouSet7.setOnClickListener { minusSetScore(scoreYouSet7)}
        plusBtnOppSet7.setOnClickListener {plusSetScore(scoreOppSet7)}
        minusBtnOppSet7.setOnClickListener {minusSetScore(scoreOppSet7)}
        // SET 8
        plusBtnYouSet8.setOnClickListener { plusSetScore(scoreYouSet8) }
        minusBtnYouSet8.setOnClickListener { minusSetScore(scoreYouSet8)}
        plusBtnOppSet8.setOnClickListener {plusSetScore(scoreOppSet8)}
        minusBtnOppSet8.setOnClickListener {minusSetScore(scoreOppSet8)}
        // SET 9
        plusBtnYouSet9.setOnClickListener { plusSetScore(scoreYouSet9) }
        minusBtnYouSet9.setOnClickListener { minusSetScore(scoreYouSet9)}
        plusBtnOppSet9.setOnClickListener {plusSetScore(scoreOppSet9)}
        minusBtnOppSet9.setOnClickListener {minusSetScore(scoreOppSet9)}
        // SET 10
        plusBtnYouSet10.setOnClickListener { plusSetScore(scoreYouSet10) }
        minusBtnYouSet10.setOnClickListener { minusSetScore(scoreYouSet10)}
        plusBtnOppSet10.setOnClickListener {plusSetScore(scoreOppSet10)}
        minusBtnOppSet10.setOnClickListener {minusSetScore(scoreOppSet10)}

        // SAVE AND CANCEL BUTTONS
        saveBtn.setOnClickListener {

            if(matchId < 0) {
                saveGameFun()
                toast("Match saved")
            }

            if(matchId >= 0) {
                val match: Match = dbHandler.getMatch(this, matchId)
                val editedMatch = editedMatch()
                toast("Match edited")
                dbHandler.updateMatch(this,match,editedMatch)
            }

            val intent = Intent(this, MatchHistory::class.java)
            clearEdits()
            matchId = -1
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        cancelBtn.setOnClickListener {
            val intent = Intent(this, MatchHistory::class.java)
            clearEdits()
            matchId = -1
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }


    //SET DATE BUTTONS
    fun pickDateBtnClicked(view: View) {
        pickDateBtn.setTextColor(Color.parseColor("#4CAF50"))
        todayBtn.setTextColor(Color.parseColor("#804CAF50"))
        // CALENDAR
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, mYear, mMonth, mDay ->

            c.set(Calendar.YEAR, mYear)
            c.set(Calendar.MONTH,mMonth)
            c.set(Calendar.DAY_OF_MONTH,mDay)
            val formatter = "yyyy/MM/dd"
            val sdf = SimpleDateFormat(formatter, Locale.US)
            val formatted = sdf.format(c.time)

            date.text = formatted.toString()
        }, year, month, day)

        dpd.show()
    }
    fun todayBtnClicked(view:View) {
        pickDateBtn.setTextColor(Color.parseColor("#804CAF50"))
        todayBtn.setTextColor(Color.parseColor("#4CAF50"))
        val c = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val formatted = c.format(formatter)
        date.text = formatted.toString()
    }


    // PLUS AND MINUS BUTTONS
    private fun plusSetScore (setMyScore : TextView) {
        // CHECK IF MATCH SCORE IS ALREADY PRESENT
        // if true it means match is already present
        if (scoringMethod == 1 && (myScore.text.toString().toInt() != 0 || oppScore.text.toString().toInt() != 0)) {
            alert {
                title = "Attention"
                message = "There is already a score for the match. If you want to proceed, that score will be erased."
                positiveButton("Proceed") {
                    clearScore()
                    setMyScore.text = (setMyScore.text.toString().toInt() + 1).toString()
                    updateMatchScore()
                    scoringMethod = 2
                }
                negativeButton ("Cancel") {}
            }.show()
        } else {
            setMyScore.text = (setMyScore.text.toString().toInt() + 1).toString()
            updateMatchScore()
            scoringMethod = 2
        }
    }

    private fun minusSetScore (setMyScore : TextView) {
        // CHECK IF MATCH SCORE IS ALREADY PRESENT
        // if true it means match is already present
        if(setMyScore.text.toString().toInt() == 0) {
            toast("Score can't be negative")
        } else {
            if (scoringMethod == 1 && (myScore.text.toString().toInt() != 0 || oppScore.text.toString().toInt() != 0)) {
                alert {
                    title = "Attention"
                    message =
                        "There is already a score for the match. If you want to proceed, that score will be erased."
                    positiveButton("Proceed") {
                        clearScore()
                        setMyScore.text = (setMyScore.text.toString().toInt() - 1).toString()
                        updateMatchScore()
                        scoringMethod = 2
                    }
                    negativeButton("Cancel") {}
                }.show()
            } else {
                setMyScore.text = (setMyScore.text.toString().toInt() - 1).toString()
                updateMatchScore()
                scoringMethod = 2
            }
        }
    }

    private fun scoreMatchPlus (textView: TextView) {
        if (checkSetsScore()) {
            alert {
                title = "Alert"
                message = "There are already scores on individual sets. If you want to proceed, those scores will be erased."
                positiveButton("Proceed") {
                    textView.text = (textView.text.toString().toInt() + 1).toString()
                    scoringMethod = 1
                    clearSetScores()
                }
                negativeButton ("Cancel") {Log.d("myError", "negative response")}
            }.show()
        } else {
            textView.text = (textView.text.toString().toInt() + 1).toString()
            scoringMethod = 1
        }
    }



    private fun scoreMatchMinus(textView: TextView) {
        val score = textView.text.toString().toInt()
        if (score > 0) {
            if (checkSetsScore()) {
                alert {
                    title = "Alert"
                    message = "There are already scores on individual sets. If you want to proceed, those scores will be erased."
                    positiveButton("Proceed") {
                        textView.text = (textView.text.toString().toInt() - 1).toString()
                        scoringMethod = 1
                        clearSetScores()
                    }
                    negativeButton ("Cancel") {Log.d("myError", "negative response")}
                }.show()
            } else {
                textView.text = (textView.text.toString().toInt() - 1).toString()
                scoringMethod = 1
            }
        } else {
            toast("Score can't be negative")
        }
    }

    // DEFINE OVERALL SCORE BASED ON SET SCORES
    private fun updateMatchScore() {
        var localMyScore = 0
        var localOppScore = 0

        // set one score
        if(scoreYouSet1.text.toString().toInt() > scoreOppSet1.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet1.text.toString().toInt() < scoreOppSet1.text.toString().toInt()) {
            localOppScore += 1
        }

        // set two score
        if(scoreYouSet2.text.toString().toInt() > scoreOppSet2.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet2.text.toString().toInt() < scoreOppSet2.text.toString().toInt()) {
            localOppScore += 1
        }

        // set three score
        if(scoreYouSet3.text.toString().toInt() > scoreOppSet3.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet3.text.toString().toInt() < scoreOppSet3.text.toString().toInt()) {
            localOppScore += 1
        }

        // set four score
        if(scoreYouSet4.text.toString().toInt() > scoreOppSet4.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet4.text.toString().toInt() < scoreOppSet4.text.toString().toInt()) {
            localOppScore += 1
        }

        // set five score
        if(scoreYouSet5.text.toString().toInt() > scoreOppSet5.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet5.text.toString().toInt() < scoreOppSet5.text.toString().toInt()) {
            localOppScore += 1
        }

        // set six score
        if(scoreYouSet6.text.toString().toInt() > scoreOppSet6.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet6.text.toString().toInt() < scoreOppSet6.text.toString().toInt()) {
            localOppScore += 1
        }

        // set seven score
        if(scoreYouSet7.text.toString().toInt() > scoreOppSet7.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet7.text.toString().toInt() < scoreOppSet7.text.toString().toInt()) {
            localOppScore += 1
        }

        // set eight score
        if(scoreYouSet8.text.toString().toInt() > scoreOppSet8.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet8.text.toString().toInt() < scoreOppSet8.text.toString().toInt()) {
            localOppScore += 1
        }

        // set nine score
        if(scoreYouSet9.text.toString().toInt() > scoreOppSet9.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet9.text.toString().toInt() < scoreOppSet9.text.toString().toInt()) {
            localOppScore += 1
        }

        // set ten score
        if(scoreYouSet10.text.toString().toInt() > scoreOppSet10.text.toString().toInt()) {
            localMyScore += 1
        } else if(scoreYouSet10.text.toString().toInt() < scoreOppSet10.text.toString().toInt()) {
            localOppScore += 1
        }

        // ASSIGN VALUES TO TEXTVIEW
        myScore.text = localMyScore.toString()
        oppScore.text = localOppScore.toString()
    }

    // CHECK IF THERE ARE ANY SET SCORES
    private fun checkSetsScore() : Boolean {
        var bool = false
        if (
            scoreYouSet1.text.toString().toInt() != 0 ||
            scoreOppSet1.text.toString().toInt() != 0 ||
            scoreYouSet2.text.toString().toInt() != 0 ||
            scoreOppSet2.text.toString().toInt() != 0 ||
            scoreYouSet3.text.toString().toInt() != 0 ||
            scoreOppSet3.text.toString().toInt() != 0 ||
            scoreYouSet4.text.toString().toInt() != 0 ||
            scoreOppSet4.text.toString().toInt() != 0 ||
            scoreYouSet5.text.toString().toInt() != 0 ||
            scoreOppSet5.text.toString().toInt() != 0 ||
            scoreYouSet6.text.toString().toInt() != 0 ||
            scoreOppSet6.text.toString().toInt() != 0 ||
            scoreYouSet7.text.toString().toInt() != 0 ||
            scoreOppSet7.text.toString().toInt() != 0 ||
            scoreYouSet8.text.toString().toInt() != 0 ||
            scoreOppSet8.text.toString().toInt() != 0 ||
            scoreYouSet9.text.toString().toInt() != 0 ||
            scoreOppSet9.text.toString().toInt() != 0 ||
            scoreYouSet10.text.toString().toInt() != 0 ||
            scoreOppSet10.text.toString().toInt() != 0
        ) {bool = true}
        // true means there are scores on the sets
        // false means there are NO scores on the sets
        return bool
    }

    // ADD AND REMOVE SET BUTTONS
    fun addSetPlusBtnClicked (view: View) {
        when {
            ninthSetLayout.isVisible -> {
                tenthSetLayout.visibility = View.VISIBLE
                addSetLayout.visibility = View.INVISIBLE
            }
            eighthSetLayout.isVisible -> ninthSetLayout.visibility = View.VISIBLE
            seventhSetLayout.isVisible -> eighthSetLayout.visibility = View.VISIBLE
            sixthSetLayout.isVisible -> seventhSetLayout.visibility = View.VISIBLE
            fifthSetLayout.isVisible -> sixthSetLayout.visibility = View.VISIBLE
            fourthSetLayout.isVisible -> fifthSetLayout.visibility = View.VISIBLE
            thirdSetLayout.isVisible -> fourthSetLayout.visibility = View.VISIBLE
            secondSetLayout.isVisible -> thirdSetLayout.visibility = View.VISIBLE
        }
        if (!secondSetLayout.isVisible) {
            secondSetLayout.visibility = View.VISIBLE
            removeSetLayout.visibility = View.VISIBLE
        }
    }

    fun removeSetMinusBtnClicked(view: View) {
        when {
            tenthSetLayout.isVisible -> {
                tenthSetLayout.visibility = View.GONE
                addSetLayout.visibility = View.VISIBLE
            }
            ninthSetLayout.isVisible -> ninthSetLayout.visibility = View.GONE
            eighthSetLayout.isVisible -> eighthSetLayout.visibility = View.GONE
            seventhSetLayout.isVisible -> seventhSetLayout.visibility = View.GONE
            sixthSetLayout.isVisible -> sixthSetLayout.visibility = View.GONE
            fifthSetLayout.isVisible -> fifthSetLayout.visibility = View.GONE
            fourthSetLayout.isVisible -> fourthSetLayout.visibility = View.GONE
            thirdSetLayout.isVisible -> thirdSetLayout.visibility = View.GONE
            secondSetLayout.isVisible -> {
                secondSetLayout.visibility = View.GONE
                removeSetLayout.visibility = View.INVISIBLE
            }

        }
    }

    // POPULATE ON EDIT MATCH
    private fun populateView(match: Match, position: Int) {
        // CHANGE ADD MATCH TO EDIT MATCH
        activityTitle.text = getString(R.string.edit_match)
        saveBtn.text = getString(R.string.save_edit)

        // POPULATE MAIN INFO
        opponentsName.setText(match.opponentName)
        courtName.setText(match.courtName)
        date.text = match.date
        myScore.text = match.myScore.toString()
        oppScore.text = match.opponentScore.toString()

        // POPULATE SET SCORES
        if (match.firstSetMyScore + match.firstSetOpponentScore > 0) {
            scoreYouSet1.text = match.firstSetMyScore.toString()
            scoreOppSet1.text = match.firstSetOpponentScore.toString()
        }

        if (match.secondSetMyScore + match.secondSetOpponentScore > 0) {
            scoreYouSet2.text = match.secondSetMyScore.toString()
            scoreOppSet2.text = match.secondSetOpponentScore.toString()
        }

        if (match.thirdSetMyScore + match.thirdSetOpponentScore > 0) {
            scoreYouSet3.text = match.thirdSetMyScore.toString()
            scoreOppSet3.text = match.thirdSetOpponentScore.toString()
        }

        if (match.fourthSetMyScore + match.fourthSetOpponentScore > 0) {
            scoreYouSet4.text = match.fourthSetMyScore.toString()
            scoreOppSet4.text = match.fourthSetOpponentScore.toString()
        }

        if (match.fifthSetMyScore + match.fifthSetOpponentScore > 0) {
            scoreYouSet5.text = match.fifthSetMyScore.toString()
            scoreOppSet5.text = match.fifthSetOpponentScore.toString()
        }

        // WRITE NOT RECORDED WHEN NECESSARY
        if (courtName.length() == 0) {courtName.hint = getString(R.string.not_recorded)}
        if (scoreYouSet1.text.isEmpty() && scoreOppSet1.text.isEmpty()) {
            scoreYouSet1.hint = getString(R.string.not_recorded)
            scoreOppSet1.hint = getString(R.string.not_recorded)}
        if (scoreYouSet2.text.isEmpty() && scoreOppSet2.text.isEmpty()) {
            scoreYouSet2.hint = getString(R.string.not_recorded)
            scoreOppSet2.hint = getString(R.string.not_recorded) }
        if (scoreYouSet3.text.isEmpty() && scoreOppSet3.text.isEmpty()) {
            scoreYouSet3.hint = getString(R.string.not_recorded)
            scoreOppSet3.hint = getString(R.string.not_recorded) }
        if (scoreYouSet4.text.isEmpty() && scoreOppSet4.text.isEmpty()) {
            scoreYouSet4.hint = getString(R.string.not_recorded)
            scoreOppSet4.hint = getString(R.string.not_recorded) }
        if (scoreYouSet5.text.isEmpty() && scoreOppSet5.text.isEmpty()) {
            scoreYouSet5.hint = getString(R.string.not_recorded)
            scoreOppSet5.hint = getString(R.string.not_recorded) }
    }

    // SAVE GAME FUNC
    private fun saveGameFun() {
        val match = Match()

        when {
            // CHECK IF MAIN VARS ARE EMPTY - OPPONENT NAME, DATE AND SCORE
            opponentsName.text.isEmpty() -> {
                toast("Enter opponent's name")
                opponentsName.requestFocus()
            }

            date.text.isEmpty() -> {
                toast("Select a date")
                date.requestFocus()
            }

            myScore.text.toString().toInt() == 0 && oppScore.text.toString().toInt() == 0  -> {
                toast("Enter match score or enter set scores")
                date.requestFocus()
            }
        }

        // IF THE MAIN VARS ARE FILLED, FINISH THE ACTIVITY AND SAVE THE DATA TO THE MATCH CLASS
        if (opponentsName.text.isNotEmpty() && date.text.isNotEmpty() && myScore.text.isNotEmpty() && oppScore.text.isNotEmpty()) {

            // IF COURT NAME NOT FILLED THEN PUT NO VALUE ON IT
            if (courtName.text.isEmpty()) {
                match.courtName = ""
            } else {
                match.courtName = courtName.text.toString()
            }

            // SET VALUES TO MATCH CLASS
            match.opponentName = opponentsName.text.toString()
            match.date = date.text.toString()
            match.myScore = myScore.text.toString().toInt()
            match.opponentScore = oppScore.text.toString().toInt()

            // ONLY STORE SCORES THAT WERE RECORDED ON THE FORM
            if (scoreYouSet1.text.toString().toInt() != 0 && scoreOppSet1.text.toString().toInt() != 0) {
                match.firstSetMyScore = scoreYouSet1.text.toString().toInt()
                match.firstSetOpponentScore = scoreOppSet1.text.toString().toInt()
            }

            if (scoreYouSet2.text.toString().toInt() != 0 && scoreOppSet2.text.toString().toInt() != 0) {
                match.secondSetMyScore = scoreYouSet2.text.toString().toInt()
                match.secondSetOpponentScore = scoreOppSet2.text.toString().toInt()
            }

            if (scoreYouSet3.text.toString().toInt() != 0 && scoreOppSet3.text.toString().toInt() != 0) {
                match.thirdSetMyScore = scoreYouSet3.text.toString().toInt()
                match.thirdSetOpponentScore = scoreOppSet3.text.toString().toInt()
            }

            if (scoreYouSet4.text.toString().toInt() != 0 && scoreOppSet4.text.toString().toInt() != 0) {
                match.fourthSetMyScore = scoreYouSet4.text.toString().toInt()
                match.fourthSetOpponentScore = scoreOppSet4.text.toString().toInt()
            }

            if (scoreYouSet5.text.toString().toInt() != 0 && scoreOppSet5.text.toString().toInt() != 0) {
                match.fifthSetMyScore = scoreYouSet5.text.toString().toInt()
                match.fifthSetOpponentScore = scoreOppSet5.text.toString().toInt()
            }

            if (scoreYouSet6.text.toString().toInt() != 0 && scoreOppSet6.text.toString().toInt() != 0) {
                match.sixthSetMyScore = scoreYouSet6.text.toString().toInt()
                match.sixthSetOpponentScore = scoreOppSet6.text.toString().toInt()
            }

            if (scoreYouSet7.text.toString().toInt() != 0 && scoreOppSet7.text.toString().toInt() != 0) {
                match.seventhSetMyScore = scoreYouSet7.text.toString().toInt()
                match.seventhSetOpponentScore = scoreOppSet7.text.toString().toInt()
            }

            if (scoreYouSet8.text.toString().toInt() != 0 && scoreOppSet8.text.toString().toInt() != 0) {
                match.eighthSetMyScore = scoreYouSet8.text.toString().toInt()
                match.eighthSetOpponentScore = scoreOppSet8.text.toString().toInt()
            }

            if (scoreYouSet9.text.toString().toInt() != 0 && scoreOppSet9.text.toString().toInt() != 0) {
                match.ninthSetMyScore = scoreYouSet9.text.toString().toInt()
                match.ninthSetOpponentScore = scoreOppSet9.text.toString().toInt()
            }

            if (scoreYouSet10.text.toString().toInt() != 0 && scoreOppSet10.text.toString().toInt() != 0) {
                match.tenthSetMyScore = scoreYouSet10.text.toString().toInt()
                match.tenthSetOpponentScore = scoreOppSet10.text.toString().toInt()
            }

            // ADD MATCH CLASS TO DB OR EDIT DB ENTRY
            dbHandler.addMatch(this, match)

        }
    }

    private fun editedMatch() : Match {
        val editMatch = Match()

        when {
            // CHECK IF MAIN VARS ARE EMPTY - OPPONENT NAME, DATE AND SCORE
            opponentsName.text.isEmpty() -> {
                toast("Enter opponent's name")
                opponentsName.requestFocus()
            }

            date.text.isEmpty() -> {
                toast("Select a date")
                date.requestFocus()
            }

            myScore.text.toString().toInt() == 0 && oppScore.text.toString().toInt() == 0  -> {
                toast("Enter match score or enter set scores")
                date.requestFocus()
            }
        }

        // IF THE MAIN VARS ARE FILLED, FINISH THE ACTIVITY AND SAVE THE DATA TO THE MATCH CLASS
        if (opponentsName.text.isNotEmpty() && date.text.isNotEmpty() && myScore.text.isNotEmpty() && oppScore.text.isNotEmpty()) {

            // IF COURT NAME NOT FILLED THEN PUT NO VALUE ON IT
            if (courtName.text.isEmpty()) {
                editMatch.courtName = ""
            } else {
                editMatch.courtName = courtName.text.toString()
            }

            // SET VALUES TO MATCH CLASS
            editMatch.opponentName = opponentsName.text.toString()
            editMatch.date = date.text.toString()
            editMatch.myScore = myScore.text.toString().toInt()
            editMatch.opponentScore = oppScore.text.toString().toInt()

            // ONLY STORE SCORES THAT WERE RECORDED ON THE FORM
            if (scoreYouSet1.text.toString().toInt() != 0 && scoreOppSet1.text.toString().toInt() != 0) {
                editMatch.firstSetMyScore = scoreYouSet1.text.toString().toInt()
                editMatch.firstSetOpponentScore = scoreOppSet1.text.toString().toInt()
            }

            if (scoreYouSet2.text.toString().toInt() != 0 && scoreOppSet2.text.toString().toInt() != 0) {
                editMatch.secondSetMyScore = scoreYouSet2.text.toString().toInt()
                editMatch.secondSetOpponentScore = scoreOppSet2.text.toString().toInt()
            }

            if (scoreYouSet3.text.toString().toInt() != 0 && scoreOppSet3.text.toString().toInt() != 0) {
                editMatch.thirdSetMyScore = scoreYouSet3.text.toString().toInt()
                editMatch.thirdSetOpponentScore = scoreOppSet3.text.toString().toInt()
            }

            if (scoreYouSet4.text.toString().toInt() != 0 && scoreOppSet4.text.toString().toInt() != 0) {
                editMatch.fourthSetMyScore = scoreYouSet4.text.toString().toInt()
                editMatch.fourthSetOpponentScore = scoreOppSet4.text.toString().toInt()
            }

            if (scoreYouSet5.text.toString().toInt() != 0 && scoreOppSet5.text.toString().toInt() != 0) {
                editMatch.fifthSetMyScore = scoreYouSet5.text.toString().toInt()
                editMatch.fifthSetOpponentScore = scoreOppSet5.text.toString().toInt()
            }

            if (scoreYouSet6.text.toString().toInt() != 0 && scoreOppSet6.text.toString().toInt() != 0) {
                editMatch.sixthSetMyScore = scoreYouSet6.text.toString().toInt()
                editMatch.sixthSetOpponentScore = scoreOppSet6.text.toString().toInt()
            }

            if (scoreYouSet7.text.toString().toInt() != 0 && scoreOppSet7.text.toString().toInt() != 0) {
                editMatch.seventhSetMyScore = scoreYouSet7.text.toString().toInt()
                editMatch.seventhSetOpponentScore = scoreOppSet7.text.toString().toInt()
            }

            if (scoreYouSet8.text.toString().toInt() != 0 && scoreOppSet8.text.toString().toInt() != 0) {
                editMatch.eighthSetMyScore = scoreYouSet8.text.toString().toInt()
                editMatch.eighthSetOpponentScore = scoreOppSet8.text.toString().toInt()
            }

            if (scoreYouSet9.text.toString().toInt() != 0 && scoreOppSet9.text.toString().toInt() != 0) {
                editMatch.ninthSetMyScore = scoreYouSet9.text.toString().toInt()
                editMatch.ninthSetOpponentScore = scoreOppSet9.text.toString().toInt()
            }

            if (scoreYouSet10.text.toString().toInt() != 0 && scoreOppSet10.text.toString().toInt() != 0) {
                editMatch.tenthSetMyScore = scoreYouSet10.text.toString().toInt()
                editMatch.tenthSetOpponentScore = scoreOppSet10.text.toString().toInt()
            }
        }

        return editMatch
    }

    // CLEAR SCORE AND TEXT FUNCTIONS

    private fun clearScore() {
        myScore.text = "0"
        oppScore.text = "0"
    }

    private fun clearSetScores() {
        scoreYouSet1.text = "0"
        scoreOppSet1.text = "0"
        scoreYouSet2.text = "0"
        scoreOppSet2.text = "0"
        scoreYouSet3.text = "0"
        scoreOppSet3.text = "0"
        scoreYouSet4.text = "0"
        scoreOppSet4.text = "0"
        scoreYouSet5.text = "0"
        scoreOppSet5.text = "0"
        scoreYouSet6.text = "0"
        scoreOppSet6.text = "0"
        scoreYouSet7.text = "0"
        scoreOppSet7.text = "0"
        scoreYouSet8.text = "0"
        scoreOppSet8.text = "0"
        scoreYouSet9.text = "0"
        scoreOppSet9.text = "0"
        scoreYouSet10.text = "0"
        scoreOppSet10.text = "0"
    }

    private fun clearEdits() {
        activityTitle.text = getString(R.string.add_match)
        saveBtn.text = getString(R.string.save_match)
        opponentsName.text.clear()
        courtName.text.clear()
        date.text = getString(R.string.date)
        myScore.text = "0"
        oppScore.text = "0"
        scoreYouSet1.text = "0"
        scoreOppSet1.text = "0"
        scoreYouSet2.text = "0"
        scoreOppSet2.text = "0"
        scoreYouSet3.text = "0"
        scoreOppSet3.text = "0"
        scoreYouSet4.text = "0"
        scoreOppSet4.text = "0"
        scoreYouSet5.text = "0"
        scoreOppSet5.text = "0"
        scoreYouSet6.text = "0"
        scoreOppSet6.text = "0"
        scoreYouSet7.text = "0"
        scoreOppSet7.text = "0"
        scoreYouSet8.text = "0"
        scoreOppSet8.text = "0"
        scoreYouSet9.text = "0"
        scoreOppSet9.text = "0"
        scoreYouSet10.text = "0"
        scoreOppSet10.text = "0"
    }
}
