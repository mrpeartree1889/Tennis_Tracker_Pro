package com.example.tennistrackerpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_add_match.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class AddMatch : AppCompatActivity() {

    // SCORING METHOD : 1 is through match overall, 2 is through set
    private var scoringMethod = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_match)

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
            val intent = Intent(this, MatchHistory::class.java)
            clearEdits()
            startActivity(intent)
        }

        cancelBtn.setOnClickListener {
            val intent = Intent(this, MatchHistory::class.java)
            clearEdits()
            startActivity(intent)
        }

    }


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
