package com.example.tennistrackerpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_add_match.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class AddMatch : AppCompatActivity() {

    // 0 no winner, 1 my win, 2 opponent win
    private var setOneWinner = 0
    private var setTwoWinner = 0
    private var setThreeWinner = 0
    private var setFourWinner = 0
    private var setFiveWinner = 0
    private var setSixWinner = 0
    private var setSevenWinner = 0
    private var setEightWinner = 0
    private var setNineWinner = 0
    private var setTenWinner = 0
    // 0 is match score, 1 is set score
    private var scoreState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_match)


        saveBtn.setOnClickListener() {
            val intent = Intent(this, MatchHistory::class.java)
            clearEdits()
            startActivity(intent)
        }

        cancelBtn.setOnClickListener() {
            val intent = Intent(this, MatchHistory::class.java)
            clearEdits()
            startActivity(intent)
        }

        plusBtnYou.setOnClickListener { scoreMatchPlus(myScore) }
        minusBtnYou.setOnClickListener { scoreMatchMinus(myScore) }

        plusBtnOpp.setOnClickListener { scoreMatchPlus(oppScore) }
        minusBtnOpp.setOnClickListener {scoreMatchMinus(oppScore) }

        plusBtnYouSet1.setOnClickListener {
            scoreSetPlus(scoreYouSet1)
            setOneWinner = setWinner(scoreYouSet1, scoreOppSet1)
            updateMatchScore(myScore, oppScore)
        }

        minusBtnYouSet1.setOnClickListener {
            scoreSetMinus(scoreYouSet1)
            setOneWinner = setWinner(scoreYouSet1, scoreOppSet1)
            updateMatchScore(myScore, oppScore)
        }

        plusBtnOppSet1.setOnClickListener {
            scoreSetPlus(scoreOppSet1)
            setOneWinner = setWinner(scoreYouSet1, scoreOppSet1)
            updateMatchScore(myScore, oppScore)
        }

        minusBtnOppSet1.setOnClickListener {
            scoreSetMinus(scoreOppSet1)
            setOneWinner = setWinner(scoreYouSet1, scoreOppSet1)
            updateMatchScore(myScore, oppScore)
        }








    }



    private fun updateMatchScore(mySetScoreTextView: TextView, oppSetScoreTextView: TextView) {
        var myScoreLocal = 0
        var oppScoreLocal = 0

        if (setOneWinner == 1) {myScoreLocal += 1}
        else if (setOneWinner == 2) {oppScoreLocal += 1}

        if (setTwoWinner == 1) {myScoreLocal += 1}
        else if (setTwoWinner == 2) {oppScoreLocal += 1}

        if (setThreeWinner == 1) {myScoreLocal += 1}
        else if (setThreeWinner == 2) {oppScoreLocal += 1}

        if (setFourWinner == 1) {myScoreLocal += 1}
        else if (setFourWinner == 2) {oppScoreLocal += 1}

        if (setFiveWinner == 1) {myScoreLocal += 1}
        else if (setFiveWinner == 2) {oppScoreLocal += 1}

        if (setSixWinner == 1) {myScoreLocal += 1}
        else if (setSixWinner == 2) {oppScoreLocal += 1}

        if (setSevenWinner == 1) {myScoreLocal += 1}
        else if (setSevenWinner == 2) {oppScoreLocal += 1}

        if (setEightWinner == 1) {myScoreLocal += 1}
        else if (setEightWinner == 2) {oppScoreLocal += 1}

        if (setNineWinner == 1) {myScoreLocal += 1}
        else if (setNineWinner == 2) {oppScoreLocal += 1}

        if (setTenWinner == 1) {myScoreLocal += 1}
        else if (setTenWinner == 2) {oppScoreLocal += 1}

        mySetScoreTextView.text = myScoreLocal.toString()
        oppSetScoreTextView.text = oppScoreLocal.toString()
    }

    fun addSetPlusBtnClicked(view: View) {
        when {
            ninthSetLayout.isVisible -> {
                tenthSetLayout.visibility = View.VISIBLE
                addSetLayout.visibility = View.GONE
            }
            eighthSetLayout.isVisible -> ninthSetLayout.visibility = View.VISIBLE
            seventhSetLayout.isVisible -> eighthSetLayout.visibility = View.VISIBLE
            sixthSetLayout.isVisible -> seventhSetLayout.visibility = View.VISIBLE
            fifthSetLayout.isVisible -> sixthSetLayout.visibility = View.VISIBLE
            fourthSetLayout.isVisible -> fifthSetLayout.visibility = View.VISIBLE
        }
        if (!fourthSetLayout.isVisible) {fourthSetLayout.visibility = View.VISIBLE}
    }

    private fun scoreMatchPlus (textView: TextView) {
        if (checkSetsScore()) {
            alert {
                title = "Alert"
                message = "There are already scores on individual sets. If you want to proceed, those scores will be erased."
                positiveButton("Proceed") {
                    textView.text = (textView.text.toString().toInt() + 1).toString()
                    scoreState = 0
                    clearSetScores()
                }
                negativeButton ("Cancel") {Log.d("myError", "negative response")}
            }.show()
        } else {
            textView.text = (textView.text.toString().toInt() + 1).toString()
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
                        scoreState = 0
                        clearSetScores()
                    }
                    negativeButton ("Cancel") {Log.d("myError", "negative response")}
                }.show()
            } else {
                textView.text = (textView.text.toString().toInt() - 1).toString()
            }
        } else {
            toast("Score can't be below 0")
        }
    }

    private fun scoreSetPlus (textView: TextView) {
        if (checkMatchScore() && scoreState == 0) {
            alert {
                title = "Alert"
                message = "There are already scores for the overall match. If you want to proceed, those scores will be erased."
                positiveButton("Proceed") {
                    textView.text = (textView.text.toString().toInt() + 1).toString()
                    scoreState = 1
                    clearScore()
                }
                negativeButton ("Cancel") {Log.d("myError", "negative response")}
            }.show()
        } else {
            textView.text = (textView.text.toString().toInt() + 1).toString()
            scoreState = 1
        }
    }

    private fun scoreSetMinus(textView: TextView) {
        val score = textView.text.toString().toInt()
        if (score > 0) {
            if (checkMatchScore() && scoreState == 0) {
                alert {
                    title = "Alert"
                    message = "There are already scores for the overall match. If you want to proceed, those scores will be erased."
                    positiveButton("Proceed") {
                        textView.text = (textView.text.toString().toInt() - 1).toString()
                        scoreState = 1
                        clearSetScores()
                    }
                    negativeButton ("Cancel") {Log.d("myError", "negative response")}
                }.show()
            } else {
                textView.text = (textView.text.toString().toInt() - 1).toString()
                scoreState = 1
            }
        } else {
            toast("Set score can't be below 0")
        }
    }



    private fun setWinner(mySetScoreTextView : TextView, oppSetScoreTextView : TextView) : Int {
        val mySetScore = mySetScoreTextView.text.toString().toInt()
        val oppSetScore = oppSetScoreTextView.text.toString().toInt()
        var result = 0

        when {
            mySetScore == oppSetScore -> result = 0
            mySetScore > oppSetScore -> result = 1
            mySetScore < oppSetScore -> result = 2
        }

        return result
    }

    private fun checkMatchScore() : Boolean {
        var bool = false
        if (
            myScore.text.toString().toInt() != 0 ||
            oppScore.text.toString().toInt() != 0
                ) {bool = true}
        // true means there are scores on the match
        // false means there are NO scores on the match
        return bool
    }
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
