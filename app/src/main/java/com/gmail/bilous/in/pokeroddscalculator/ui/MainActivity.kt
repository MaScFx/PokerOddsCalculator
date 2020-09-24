package com.gmail.bilous.`in`.pokeroddscalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.bilous.`in`.pokeroddscalculator.R
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Rank
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.findPokerCombination
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.getFullDeck
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.getRandomCardFromDeckAndDeleteFromSet

class MainActivity : AppCompatActivity() {
    public final val TAG: String = "MyLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val setTest = HashSet<Card>()
        setTest.add(Card(Suit.CLUBS,Rank.ACE))
        setTest.add(Card(Suit.HEARTS,Rank.QUEEN))
        setTest.add(Card(Suit.CLUBS,Rank.QUEEN))
        setTest.add(Card(Suit.DIAMONDS,Rank.TWO))
        setTest.add(Card(Suit.CLUBS,Rank.JACK))
        setTest.add(Card(Suit.CLUBS,Rank.KING))
        setTest.add(Card(Suit.CLUBS,Rank.TWO))


        val response = findPokerCombination(setTest)

        Log.i(TAG, "Kiker: ${response.kiker}")
        Log.i(TAG, "pokerCombinations: ${response.pokerCombinations}")


//        var set = getFullDeck()
//        var i = 0
//        val card=getRandomCardFromDeckAndDeleteFromSet(set)
//        set.iterator().forEach { it ->
//            i++
//            Log.i(TAG, "count =$i onCreate: $it")
//        }
//        Log.i(TAG, "MyCard: $card")

    }
}