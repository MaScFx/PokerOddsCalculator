package com.gmail.bilous.`in`.pokeroddscalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.bilous.`in`.pokeroddscalculator.R
import com.gmail.bilous.`in`.pokeroddscalculator.addToLog
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Rank
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.*
import java.util.*
import kotlin.collections.HashSet

class MainActivity : AppCompatActivity() {
    public final val TAG: String = "MyLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val setTest = HashSet<Card>()
        setTest.add(Card(Suit.DIAMONDS,Rank.ACE))
        setTest.add(Card(Suit.HEARTS,Rank.QUEEN))
        setTest.add(Card(Suit.DIAMONDS,Rank.QUEEN))
        setTest.add(Card(Suit.DIAMONDS,Rank.SEVEN))
        setTest.add(Card(Suit.CLUBS,Rank.JACK))
        setTest.add(Card(Suit.DIAMONDS,Rank.TREE))
        setTest.add(Card(Suit.DIAMONDS,Rank.FIVE))


        val response = isFlush(setTest)

        addToLog(response.isFlush.toString())
        addToLog(response.flushSet.toString())
        addToLog("________________MAIN__________")
        val queue:Queue<String> = LinkedList<String>()
        queue.add("1")
        queue.add("2")
        queue.add("3")
        Log.i(TAG, queue.remove())
        Log.i(TAG, queue.remove())
        Log.i(TAG, queue.remove())
        Log.i(TAG, queue.remove())
        Log.i(TAG, queue.remove())


    }
}