package com.example.tennistrackerpro.activities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.tennistrackerpro.activities.Models.Match
import com.example.tennistrackerpro.activities.Models.Statistics
import com.example.tennistrackerpro.activities.Models.scoreOpponents
import org.jetbrains.anko.toast
import java.lang.Exception

class DBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int):
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "MyData.db"
        private val DATABASE_VERSION = 1

        val MATCHES_TABLE_NAME = "Matches"
        val COLUMN_MATCHID = "MatchId"
        val COLUMN_OPPONENT_NAME = "OpponentName"
        val COLUMN_COURT_NAME = "CourtName"
        val COLUMN_DATE = "Date"
        val COLUMN_MY_SCORE = "MyScore"
        val COLUMN_OPPONENT_SCORE = "OpponentScore"
        val COLUMN_FIRST_SET_MY_SCORE = "FirstSetMyScore"
        val COLUMN_FIRST_SET_OPPONENT_SCORE = "FirstSetOpponentScore"
        val COLUMN_SECOND_SET_MY_SCORE = "SecondSetMyScore"
        val COLUMN_SECOND_SET_OPPONENT_SCORE = "SecondSetOpponentScore"
        val COLUMN_THIRD_SET_MY_SCORE = "ThirdSetMyScore"
        val COLUMN_THIRD_SET_OPPONENT_SCORE = "ThirdSetOpponentScore"
        val COLUMN_FOURTH_SET_MY_SCORE = "FourthSetMyScore"
        val COLUMN_FOURTH_SET_OPPONENT_SCORE = "FourthSetOpponentScore"
        val COLUMN_FIFTH_SET_MY_SCORE = "FifthSetMyScore"
        val COLUMN_FIFTH_SET_OPPONENT_SCORE = "FifthSetOpponentScore"
        val COLUMN_SIXTH_SET_MY_SCORE = "SixthSetMyScore"
        val COLUMN_SIXTH_SET_OPPONENT_SCORE = "SixthSetOpponentScore"
        val COLUMN_SEVENTH_SET_MY_SCORE = "SeventhSetMyScore"
        val COLUMN_SEVENTH_SET_OPPONENT_SCORE = "SeventhSetOpponentScore"
        val COLUMN_EIGHTH_SET_MY_SCORE = "EighthSetMyScore"
        val COLUMN_EIGHTH_SET_OPPONENT_SCORE = "EighthSetOpponentScore"
        val COLUMN_NINTH_SET_MY_SCORE = "NinthSetMyScore"
        val COLUMN_NINTH_SET_OPPONENT_SCORE = "NinthSetOpponentScore"
        val COLUMN_TENTH_SET_MY_SCORE = "TenthSetMyScore"
        val COLUMN_TENTH_SET_OPPONENT_SCORE = "TenthSetOpponentScore"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_MATCHES_TABLE: String = ("CREATE TABLE $MATCHES_TABLE_NAME (" +
                "$COLUMN_MATCHID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_OPPONENT_NAME TEXT," +
                "$COLUMN_COURT_NAME TEXT," +
                "$COLUMN_DATE TEXT," +
                "$COLUMN_MY_SCORE INTEGER," +
                "$COLUMN_OPPONENT_SCORE INTEGER," +
                "$COLUMN_FIRST_SET_MY_SCORE INTEGER," +
                "$COLUMN_FIRST_SET_OPPONENT_SCORE INTEGER," +
                "$COLUMN_SECOND_SET_MY_SCORE INTEGER," +
                "$COLUMN_SECOND_SET_OPPONENT_SCORE INTEGER," +
                "$COLUMN_THIRD_SET_MY_SCORE INTEGER," +
                "$COLUMN_THIRD_SET_OPPONENT_SCORE INTEGER," +
                "$COLUMN_FOURTH_SET_MY_SCORE INTEGER," +
                "$COLUMN_FOURTH_SET_OPPONENT_SCORE INTEGER," +
                "$COLUMN_FIFTH_SET_MY_SCORE INTEGER," +
                "$COLUMN_FIFTH_SET_OPPONENT_SCORE INTEGER," +
                "$COLUMN_SIXTH_SET_MY_SCORE INTEGER," +
                "$COLUMN_SIXTH_SET_OPPONENT_SCORE INTEGER," +
                "$COLUMN_SEVENTH_SET_MY_SCORE INTEGER," +
                "$COLUMN_SEVENTH_SET_OPPONENT_SCORE INTEGER," +
                "$COLUMN_EIGHTH_SET_MY_SCORE INTEGER," +
                "$COLUMN_EIGHTH_SET_OPPONENT_SCORE INTEGER," +
                "$COLUMN_NINTH_SET_MY_SCORE INTEGER," +
                "$COLUMN_NINTH_SET_OPPONENT_SCORE INTEGER," +
                "$COLUMN_TENTH_SET_MY_SCORE INTEGER," +
                "$COLUMN_TENTH_SET_OPPONENT_SCORE INTEGER)")
        db?.execSQL(CREATE_MATCHES_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun getMatches(mCtx: Context) : ArrayList<Match> {
        val qry = "SELECT * FROM $MATCHES_TABLE_NAME ORDER BY $COLUMN_DATE ASC"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val matches = ArrayList<Match>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No records found", Toast.LENGTH_SHORT).show() else {
            while (cursor.moveToNext()) {
                val match = Match()
                // MAIN INFO
                match.MatchID = cursor.getInt(cursor.getColumnIndex(COLUMN_MATCHID))
                match.opponentName = cursor.getString(cursor.getColumnIndex(COLUMN_OPPONENT_NAME))
                match.date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
                match.myScore = cursor.getInt(cursor.getColumnIndex(COLUMN_MY_SCORE))
                match.opponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_OPPONENT_SCORE)))

                // SET INFO
                match.firstSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_FIRST_SET_MY_SCORE)))
                match.firstSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_FIRST_SET_OPPONENT_SCORE)))

                match.secondSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_SECOND_SET_MY_SCORE)))
                match.secondSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_SECOND_SET_OPPONENT_SCORE)))

                match.thirdSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_THIRD_SET_MY_SCORE)))
                match.thirdSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_THIRD_SET_OPPONENT_SCORE)))

                match.fourthSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_FOURTH_SET_MY_SCORE)))
                match.fourthSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_FOURTH_SET_OPPONENT_SCORE)))

                match.fifthSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_FIFTH_SET_MY_SCORE)))
                match.fifthSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_FIFTH_SET_OPPONENT_SCORE)))

                match.sixthSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_SIXTH_SET_MY_SCORE)))
                match.sixthSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_SIXTH_SET_OPPONENT_SCORE)))

                match.seventhSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_SEVENTH_SET_MY_SCORE)))
                match.seventhSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_SEVENTH_SET_OPPONENT_SCORE)))

                match.eighthSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_EIGHTH_SET_MY_SCORE)))
                match.eighthSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_EIGHTH_SET_OPPONENT_SCORE)))

                match.ninthSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_NINTH_SET_MY_SCORE)))
                match.ninthSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_NINTH_SET_OPPONENT_SCORE)))

                match.tenthSetMyScore = cursor.getInt(cursor.getColumnIndex((COLUMN_TENTH_SET_MY_SCORE)))
                match.tenthSetOpponentScore = cursor.getInt(cursor.getColumnIndex((COLUMN_TENTH_SET_OPPONENT_SCORE)))


                matches.add(match)
            }
        }
        cursor.close()
        db.close()
        return matches
    }

    fun getScoresOpp(mCtx: Context) : ArrayList<scoreOpponents> {
        val qry = "SELECT $COLUMN_OPPONENT_NAME , $COLUMN_MY_SCORE , $COLUMN_OPPONENT_SCORE FROM $MATCHES_TABLE_NAME"
        val db = this.readableDatabase
        val oppNameCursor = db.query(true, MATCHES_TABLE_NAME, arrayOf(COLUMN_OPPONENT_NAME), null, null, COLUMN_OPPONENT_NAME, null, COLUMN_OPPONENT_NAME, null)
        val cursor = db.rawQuery(qry, null)
        val scoreOpponents = ArrayList<scoreOpponents>()

        if (cursor.count == 0 || oppNameCursor.count == 0)
            Toast.makeText(mCtx, "No records found", Toast.LENGTH_SHORT).show() else {
            while (oppNameCursor.moveToNext()) {
                val oppScore = scoreOpponents()
                oppScore.opponentName = oppNameCursor.getString(oppNameCursor.getColumnIndex(COLUMN_OPPONENT_NAME))
                var matchesWon = 0
                var matchesLost = 0
                while (cursor.moveToNext()) {
                    if (oppScore.opponentName == cursor.getString(cursor.getColumnIndex(COLUMN_OPPONENT_NAME))) {
                        var mySet = cursor.getInt(cursor.getColumnIndex(COLUMN_MY_SCORE))
                        var oppSet = cursor.getInt(cursor.getColumnIndex(COLUMN_OPPONENT_SCORE))
                        if (mySet > oppSet) {matchesWon += 1}
                        if (oppSet > mySet) {matchesLost += 1}
                        Log.d("errorFinding","Score at $cursor is $matchesWon vs $matchesLost")
                    }
                }

                oppScore.matchesWon = matchesWon
                oppScore.matchesLost = matchesLost
                cursor.moveToFirst()

                scoreOpponents.add(oppScore)
            }

        }

        oppNameCursor.close()
        cursor.close()
        db.close()
        return scoreOpponents
    }

    fun addMatch(mCtx: Context, match: Match) {
        val values = ContentValues()
        values.put(COLUMN_OPPONENT_NAME, match.opponentName)
        values.put(COLUMN_COURT_NAME, match.courtName)
        values.put(COLUMN_DATE, match.date)
        values.put(COLUMN_MY_SCORE, match.myScore)
        values.put(COLUMN_OPPONENT_SCORE, match.opponentScore)

        if (match.firstSetMyScore + match.firstSetOpponentScore > 0) {
            values.put(COLUMN_FIRST_SET_MY_SCORE, match.firstSetMyScore)
            values.put(COLUMN_FIRST_SET_OPPONENT_SCORE, match.firstSetOpponentScore) }
        if (match.secondSetMyScore + match.secondSetOpponentScore > 0) {
            values.put(COLUMN_SECOND_SET_MY_SCORE, match.secondSetMyScore)
            values.put(COLUMN_SECOND_SET_OPPONENT_SCORE, match.secondSetOpponentScore) }
        if (match.thirdSetMyScore + match.thirdSetOpponentScore > 0) {
            values.put(COLUMN_THIRD_SET_MY_SCORE, match.thirdSetMyScore)
            values.put(COLUMN_THIRD_SET_OPPONENT_SCORE, match.thirdSetOpponentScore) }
        if (match.fourthSetMyScore + match.fourthSetOpponentScore > 0) {
            values.put(COLUMN_FOURTH_SET_MY_SCORE, match.fourthSetMyScore)
            values.put(COLUMN_FOURTH_SET_OPPONENT_SCORE, match.fourthSetOpponentScore) }
        if (match.fifthSetMyScore + match.fifthSetOpponentScore > 0) {
            values.put(COLUMN_FIFTH_SET_MY_SCORE, match.fifthSetMyScore)
            values.put(COLUMN_FIFTH_SET_OPPONENT_SCORE, match.fifthSetOpponentScore) }
        if (match.sixthSetMyScore + match.sixthSetOpponentScore > 0) {
            values.put(COLUMN_SIXTH_SET_MY_SCORE, match.sixthSetMyScore)
            values.put(COLUMN_SIXTH_SET_OPPONENT_SCORE, match.sixthSetOpponentScore) }
        if (match.seventhSetMyScore + match.seventhSetOpponentScore > 0) {
            values.put(COLUMN_SEVENTH_SET_MY_SCORE, match.seventhSetMyScore)
            values.put(COLUMN_SEVENTH_SET_OPPONENT_SCORE, match.seventhSetOpponentScore) }
        if (match.eighthSetMyScore + match.eighthSetOpponentScore > 0) {
            values.put(COLUMN_EIGHTH_SET_MY_SCORE, match.eighthSetMyScore)
            values.put(COLUMN_EIGHTH_SET_OPPONENT_SCORE, match.eighthSetOpponentScore) }
        if (match.ninthSetMyScore + match.ninthSetOpponentScore > 0) {
            values.put(COLUMN_NINTH_SET_MY_SCORE, match.ninthSetMyScore)
            values.put(COLUMN_NINTH_SET_OPPONENT_SCORE, match.ninthSetOpponentScore) }
        if (match.tenthSetMyScore + match.tenthSetOpponentScore > 0) {
            values.put(COLUMN_TENTH_SET_MY_SCORE, match.tenthSetMyScore)
            values.put(COLUMN_TENTH_SET_OPPONENT_SCORE, match.tenthSetOpponentScore) }

        val db = this.writableDatabase

        try {
            db.insert(MATCHES_TABLE_NAME, null, values)
            Toast.makeText(mCtx, "Match added", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun getMatch(mCtx: Context, matchIndex: Int) : Match {
        val db = this.writableDatabase
        val selection = "SELECT * FROM $MATCHES_TABLE_NAME WHERE $COLUMN_MATCHID = $matchIndex"

        val cursor = db.rawQuery(selection, null)
        cursor.moveToFirst()

        var match = Match()
        match.MatchID = cursor.getInt(cursor.getColumnIndex(COLUMN_MATCHID))
        match.opponentName = cursor.getString(cursor.getColumnIndex(COLUMN_OPPONENT_NAME))
        match.courtName = cursor.getString(cursor.getColumnIndex(COLUMN_COURT_NAME))
        match.date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
        match.myScore = cursor.getInt(cursor.getColumnIndex(COLUMN_MY_SCORE))
        match.opponentScore = cursor.getInt(cursor.getColumnIndex(COLUMN_OPPONENT_SCORE))
        match.firstSetMyScore = cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_SET_MY_SCORE))
        match.firstSetOpponentScore = cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_SET_OPPONENT_SCORE))
        match.secondSetMyScore = cursor.getInt(cursor.getColumnIndex(COLUMN_SECOND_SET_MY_SCORE))
        match.secondSetOpponentScore = cursor.getInt(cursor.getColumnIndex(COLUMN_SECOND_SET_OPPONENT_SCORE))
        match.thirdSetMyScore = cursor.getInt(cursor.getColumnIndex(COLUMN_THIRD_SET_MY_SCORE))
        match.thirdSetOpponentScore = cursor.getInt(cursor.getColumnIndex(COLUMN_THIRD_SET_OPPONENT_SCORE))
        match.fourthSetMyScore = cursor.getInt(cursor.getColumnIndex(COLUMN_FOURTH_SET_MY_SCORE))
        match.fourthSetOpponentScore = cursor.getInt(cursor.getColumnIndex(COLUMN_FOURTH_SET_OPPONENT_SCORE))
        match.fifthSetMyScore = cursor.getInt(cursor.getColumnIndex(COLUMN_FIFTH_SET_MY_SCORE))
        match.fifthSetOpponentScore = cursor.getInt(cursor.getColumnIndex(COLUMN_FIFTH_SET_OPPONENT_SCORE))

        // ADD FUNCTIONALITY FOR SETS 6, 7, 8, 9 AND 10

        cursor.close()
        db.close()
        return match
    }

    fun updateMatch(mCtx: Context, oldMatch:Match,newMatch:Match) {
        val db = this.writableDatabase
        val opponentName = newMatch.opponentName
        val courtName = newMatch.courtName
        val matchDate = newMatch.date
        val myScore = newMatch.myScore
        val opponentScore = newMatch.opponentScore
        val firstSetMyScore = newMatch.firstSetMyScore
        val firstSetOpponentScore = newMatch.firstSetOpponentScore
        val secondSetMyScore = newMatch.secondSetMyScore
        val secondSetOpponentScore = newMatch.secondSetOpponentScore
        val thirdSetMyScore = newMatch.thirdSetMyScore
        val thirdSetOpponentScore = newMatch.thirdSetOpponentScore
        val fourthSetMyScore = newMatch.fourthSetMyScore
        val fourthSetOpponentScore = newMatch.fourthSetOpponentScore
        val fifthSetMyScore = newMatch.fifthSetMyScore
        val fifthSetOpponentScore = newMatch.fifthSetOpponentScore

        val contentValues = ContentValues().apply {
            put(COLUMN_OPPONENT_NAME, opponentName)
            put(COLUMN_COURT_NAME, courtName)
            put(COLUMN_DATE, matchDate)
            put(COLUMN_MY_SCORE, myScore)
            put(COLUMN_OPPONENT_SCORE, opponentScore)
            put(COLUMN_FIRST_SET_MY_SCORE, firstSetMyScore)
            put(COLUMN_FIRST_SET_OPPONENT_SCORE, firstSetOpponentScore)
            put(COLUMN_SECOND_SET_MY_SCORE, secondSetMyScore)
            put(COLUMN_SECOND_SET_OPPONENT_SCORE, secondSetOpponentScore)
            put(COLUMN_THIRD_SET_MY_SCORE, thirdSetMyScore)
            put(COLUMN_THIRD_SET_OPPONENT_SCORE, thirdSetOpponentScore)
            put(COLUMN_FOURTH_SET_MY_SCORE, fourthSetMyScore)
            put(COLUMN_FOURTH_SET_OPPONENT_SCORE, fourthSetOpponentScore)
            put(COLUMN_FIFTH_SET_MY_SCORE, fifthSetMyScore)
            put(COLUMN_FIFTH_SET_OPPONENT_SCORE, fifthSetOpponentScore)
        }

        val selection = "$COLUMN_MATCHID LIKE ?"
        val selectionArgs = arrayOf(oldMatch.MatchID.toString())

        val count = db.update(MATCHES_TABLE_NAME, contentValues, selection, selectionArgs)
        db.close()
    }

    fun deleteMatch(mCtx: Context, match: Match) {
        val db = this.writableDatabase
        val selection = "$COLUMN_MATCHID LIKE ?"
        val selectionArgs  = arrayOf(match.MatchID.toString())

        val deletedRows = db.delete(MATCHES_TABLE_NAME, selection, selectionArgs)
    }

    fun mainStatistics(mCtx: Context) : Statistics {
        val db = this.readableDatabase
        val mainStatistics = Statistics()
        val qry = "SELECT * FROM $MATCHES_TABLE_NAME ORDER BY $COLUMN_DATE ASC"
        val cursor = db.rawQuery(qry, null)

        // LOCAL VARS
        var matchesWon : Int = 0
        var matchesLost : Int = 0
        var setsWon : Int = 0
        var setsLost : Int = 0
        var gamesWon : Int = 0
        var gamesLost : Int = 0

        if (cursor.count == 0)
            mCtx.toast("No records found") else {
            while (cursor.moveToNext()) {
                // MATCH WINS VS LOSS COUNT
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_MY_SCORE)) > cursor.getInt(cursor.getColumnIndex(COLUMN_OPPONENT_SCORE)))
                    matchesWon += 1 else if (cursor.getInt(cursor.getColumnIndex(COLUMN_MY_SCORE)) < cursor.getInt(cursor.getColumnIndex(COLUMN_OPPONENT_SCORE)))
                    matchesLost += 1

                // SETS WIN VS LOST COUNT
                // set 1
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_SET_MY_SCORE)) > cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_SET_OPPONENT_SCORE)))
                    setsWon += 1 else if (cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_SET_MY_SCORE)) < cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_SET_OPPONENT_SCORE)))
                    setsLost += 1
                // set 2
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_SECOND_SET_MY_SCORE)) > cursor.getInt(cursor.getColumnIndex(COLUMN_SECOND_SET_OPPONENT_SCORE)))
                    setsWon += 1 else if (cursor.getInt(cursor.getColumnIndex(COLUMN_SECOND_SET_MY_SCORE)) < cursor.getInt(cursor.getColumnIndex(COLUMN_SECOND_SET_OPPONENT_SCORE)))
                    setsLost += 1
                // set 3
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_THIRD_SET_MY_SCORE)) > cursor.getInt(cursor.getColumnIndex(COLUMN_THIRD_SET_OPPONENT_SCORE)))
                    setsWon += 1 else if (cursor.getInt(cursor.getColumnIndex(COLUMN_THIRD_SET_MY_SCORE)) < cursor.getInt(cursor.getColumnIndex(COLUMN_THIRD_SET_OPPONENT_SCORE)))
                    setsLost += 1
                // set 4
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_FOURTH_SET_MY_SCORE)) > cursor.getInt(cursor.getColumnIndex(COLUMN_FOURTH_SET_OPPONENT_SCORE)))
                    setsWon += 1 else if (cursor.getInt(cursor.getColumnIndex(COLUMN_FOURTH_SET_MY_SCORE)) < cursor.getInt(cursor.getColumnIndex(COLUMN_FOURTH_SET_OPPONENT_SCORE)))
                    setsLost += 1
                // set 5
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_FIFTH_SET_MY_SCORE)) > cursor.getInt(cursor.getColumnIndex(COLUMN_FIFTH_SET_OPPONENT_SCORE)))
                    setsWon += 1 else if (cursor.getInt(cursor.getColumnIndex(COLUMN_FIFTH_SET_MY_SCORE)) < cursor.getInt(cursor.getColumnIndex(COLUMN_FIFTH_SET_OPPONENT_SCORE)))
                    setsLost += 1

                // GAMES WON VS LOST COUNT
                gamesWon += (cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_SET_MY_SCORE)) +
                            cursor.getInt(cursor.getColumnIndex(COLUMN_SECOND_SET_MY_SCORE)) +
                            cursor.getInt(cursor.getColumnIndex(COLUMN_THIRD_SET_MY_SCORE)) +
                            cursor.getInt(cursor.getColumnIndex(COLUMN_FOURTH_SET_MY_SCORE)) +
                            cursor.getInt(cursor.getColumnIndex(COLUMN_FIFTH_SET_MY_SCORE)))

                gamesLost += (cursor.getInt(cursor.getColumnIndex(COLUMN_FIRST_SET_OPPONENT_SCORE)) +
                            cursor.getInt(cursor.getColumnIndex(COLUMN_SECOND_SET_OPPONENT_SCORE)) +
                            cursor.getInt(cursor.getColumnIndex(COLUMN_THIRD_SET_OPPONENT_SCORE)) +
                            cursor.getInt(cursor.getColumnIndex(COLUMN_FOURTH_SET_OPPONENT_SCORE)) +
                            cursor.getInt(cursor.getColumnIndex(COLUMN_FIFTH_SET_OPPONENT_SCORE)))
            }
        }

        mainStatistics.matchesWon = matchesWon
        mainStatistics.matchesLost = matchesLost
        mainStatistics.setsWon = setsWon
        mainStatistics.setsLost = setsLost
        mainStatistics.gamesWon = gamesWon
        mainStatistics.gamesLost = gamesLost

        return mainStatistics
    }
}