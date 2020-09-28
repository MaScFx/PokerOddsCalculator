package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.responses.FlushResponse
import java.lang.IllegalArgumentException

fun findStraightFromSortedList(cardsList: List<Card>): Boolean {
    if (cardsList.size < 5) return false

    val cardsPowerList = ArrayList<Int>()
    if (cardsList.last().rank.power == 14) cardsPowerList.add(1)

    cardsList.forEach { cardsPowerList.add(it.rank.power) }

    var sequenceCount = 1
    var previousRank = cardsPowerList[0]
    cardsPowerList.forEach {
        if (it - previousRank <= 1) sequenceCount++ else sequenceCount = 1
        previousRank = it
        if (sequenceCount >= 5) return true
    }
    return false
}

fun findFlushFromList(cardsList: List<Card>): Boolean {
    if (cardsList.size < 4) return false
    var diamondsCount = 0
    var clubsCount = 0
    var heartsCount = 0
    var spadesCount = 0
    cardsList.forEach {
        if (Suit.DIAMONDS == it.suit) diamondsCount++
        if (Suit.CLUBS == it.suit) clubsCount++
        if (Suit.HEARTS == it.suit) heartsCount++
        if (Suit.SPADES == it.suit) spadesCount++
    }
    return (diamondsCount >= 4 || clubsCount >= 4 || heartsCount >= 4 || spadesCount >= 4)
}

fun findFlushInSet(cardSet: Set<Card>): FlushResponse {
    if (cardSet.size < 4) return FlushResponse(false)
    if (cardSet.size > 7) throw IllegalArgumentException("Too much Cards")

    val diamondsSet = mutableSetOf<Card>()
    val clubsSet = mutableSetOf<Card>()
    val heartsSet = mutableSetOf<Card>()
    val spadesSet = mutableSetOf<Card>()

    cardSet.forEach {
        when (it.suit) {
            Suit.DIAMONDS -> diamondsSet.add(it)
            Suit.CLUBS -> clubsSet.add(it)
            Suit.HEARTS -> heartsSet.add(it)
            Suit.SPADES -> spadesSet.add(it)
        }
    }
    when {
        diamondsSet.size >= 4 -> return FlushResponse(true, removeLowPowerCardsInFlush(diamondsSet))
        clubsSet.size >= 4 -> return FlushResponse(true, removeLowPowerCardsInFlush(clubsSet))
        heartsSet.size >= 4 -> return FlushResponse(true, removeLowPowerCardsInFlush(heartsSet))
        spadesSet.size >= 4 -> return FlushResponse(true, removeLowPowerCardsInFlush(spadesSet))
    }
    return FlushResponse(false)
}

private fun removeLowPowerCardsInFlush(oneSuitSet: MutableSet<Card>): Set<Card> {
    if (oneSuitSet.size < 4) throw IllegalArgumentException("Argument must have minimum 4 elements")
    while (oneSuitSet.size >= 4) {
        if (oneSuitSet.size == 4) {
            return oneSuitSet
        } else {
            oneSuitSet.remove(oneSuitSet.minByOrNull { it.rank.power })
        }
    }
    return setOf()
}

