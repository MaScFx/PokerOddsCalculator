package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.PokerCombinations
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Rank
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.responses.PokerCombinationResponse
import java.lang.IllegalArgumentException


fun findPokerCombination(cardSet: Set<Card>): PokerCombinationResponse {
    if (cardSet.isEmpty()) throw IllegalArgumentException("Empty cardSet!")

    val sortedList = cardSet.sortedBy { it.rank.power }
    /////////////////////////////
    addToLog(findStraightFromSortedList(sortedList).toString())
    addToLog(findFlushFromList(sortedList).toString())
    addToLog(sortedList.last().rank.power.toString())
    ////////////////////////////

    //Royal flush
    val flush = convertSetToFlushIfItIs(sortedList.toHashSet())
    addToLog("flush.isFlush"+flush.isFlush.toString())
    addToLog("flush.setFlush."+flush.setFlush.toString())
    addToLog("flush.setFlush.last().rank.toString()"+flush.setFlush.last().rank.toString())
    if (findStraightFromSortedList(sortedList) && flush.isFlush && flush.setFlush.last().rank.power == 14
    )
        return PokerCombinationResponse(PokerCombinations.RoyalFlush, Card(Suit.SPADES, Rank.TWO))


    return PokerCombinationResponse(PokerCombinations.HighCard, Card(Suit.CLUBS, Rank.ACE))
}

