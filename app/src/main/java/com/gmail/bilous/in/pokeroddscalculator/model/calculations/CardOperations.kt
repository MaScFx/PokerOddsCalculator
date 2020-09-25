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

fun convertSetToFlushIfItIs(cardSet: Set<Card>): FlushResponse {
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

    if (diamondsSet.size < 4 && clubsSet.size < 4 && heartsSet.size < 4 && spadesSet.size < 4)
        return FlushResponse(false)
    val allSets = mutableSetOf(diamondsSet,clubsSet,heartsSet,spadesSet)
    while (true){
        allSets.forEach {
            it.sortedBy { it.rank.power }
            if (it.size==4) return FlushResponse(true,it)
            if (it.size>4) {
                it.remove(it.first())
            }
        }
    }
}
