package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.PokerCombinations
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Rank
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.responses.PokerCombinationResponse
import java.lang.IllegalArgumentException
import java.util.*


fun findPokerCombination(cardSet: TreeSet<Card>): PokerCombinationResponse {
    if (cardSet.isEmpty()) throw IllegalArgumentException("Empty cardSet!")

    val flush = isFlush(cardSet)
    val straight = isStraight(cardSet)

    //Royal flush
//    if (straight && flush.isFlush && flush.flushSet.last().rank.power == 14)
//        return PokerCombinationResponse(PokerCombinations.RoyalFlush, Card(Suit.SPADES, Rank.TWO))
//
//    //Straight flush
//    if (straight && flush.isFlush)
//        return PokerCombinationResponse(PokerCombinations.StraightFlush, findKiker(cardSet,flush.flushSet))
//
//    // Four of a kind
//    if (isQuads(cardSet))
//        return PokerCombinationResponse(PokerCombinations.FourOfAKind, findKiker(cardSet,flush.flushSet))


    return PokerCombinationResponse(PokerCombinations.HighCard, Card(Suit.CLUBS, Rank.ACE))
}

