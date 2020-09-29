package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.PokerCombinations
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Rank
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.responses.PokerCombinationResponse
import java.lang.IllegalArgumentException


fun findPokerCombination(cardSet: Set<Card>): PokerCombinationResponse {
    if (cardSet.isEmpty()) throw IllegalArgumentException("Empty cardSet!")

    val flush = findFlushInSet(cardSet)
    val straight = findStraightInSet(cardSet)

    //Royal flush
    if (straight && flush.isFlush && flush.flushSet.last().rank.power == 14)
        return PokerCombinationResponse(PokerCombinations.RoyalFlush, Card(Suit.SPADES, Rank.TWO))

    //Straight flush
    if (straight && flush.isFlush)
        return PokerCombinationResponse(PokerCombinations.StraightFlush, findKiker(cardSet,flush.flushSet))

    // Four of a kind
    if (findFourOfAKindInSet(cardSet))
        return PokerCombinationResponse(PokerCombinations.FourOfAKind, findKiker(cardSet,flush.flushSet))


    return PokerCombinationResponse(PokerCombinations.HighCard, Card(Suit.CLUBS, Rank.ACE))
}

