package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Rank
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit
import java.lang.IllegalArgumentException
import java.util.*

fun isStraight(cardsSet: TreeSet<Card>): Int {
    if (cardsSet.size < 5) return -1
    val cardsPowerSet = TreeSet<Int>()
    if (cardsSet.contains(Card(Suit.SPADES, Rank.ACE)) || cardsSet.contains( Card(Suit.DIAMONDS, Rank.ACE)) ||
        cardsSet.contains(Card(Suit.HEARTS, Rank.ACE)) || cardsSet.contains( Card(Suit.CLUBS, Rank.ACE))) {
        cardsPowerSet.add(1)
    }
    cardsSet.forEach { cardsPowerSet.add(it.rank.power) }

    var sequenceCount = 1
    var previousRank = cardsPowerSet.pollLast()
    val descendingSet = cardsPowerSet.descendingSet()
    val straightSet= TreeSet<Int>()
    straightSet.add(previousRank)

    descendingSet.forEach {
        if (previousRank - it == 1){
            sequenceCount++
            straightSet.add(it)
        }
        else {
            sequenceCount = 1
            straightSet.clear()
        }
        previousRank = it
        if (sequenceCount >= 5) return straightSet.last()
    }
    return -1
}

fun isTriple(cardsSet: TreeSet<Card>):Int{

    for (r in Rank.values().reversedArray()){
        if (findSomeRanks(cardsSet,r,3)) return r.power
    }
    return -1
}

fun isFlush(cardSet: TreeSet<Card>): Int {
    if (cardSet.size < 4) return -1
    if (cardSet.size > 7) throw IllegalArgumentException("Too much Cards")

    val diamondsSet = TreeSet<Card>()
    val clubsSet = TreeSet<Card>()
    val heartsSet = TreeSet<Card>()
    val spadesSet = TreeSet<Card>()

    cardSet.forEach {
        when (it.suit) {
            Suit.DIAMONDS -> diamondsSet.add(it)
            Suit.CLUBS -> clubsSet.add(it)
            Suit.HEARTS -> heartsSet.add(it)
            Suit.SPADES -> spadesSet.add(it)
        }
    }
    return when {
        diamondsSet.size >= 4 -> diamondsSet.last().rank.power
        clubsSet.size >= 4 -> clubsSet.last().rank.power
        heartsSet.size >= 4 -> heartsSet.last().rank.power
        spadesSet.size >= 4 -> spadesSet.last().rank.power
        else -> -1
    }
}

fun isQuads(cardsSet: Set<Card>):Int{
    for (r in Rank.values()){
        if (findSomeRanks(cardsSet,r,4)) return r.power
    }
    return -1
}

fun findKiker(allCards: Set<Card>, excludedCards: Set<Card>): Card {
    val mutableAllCards = allCards.toMutableSet()
    mutableAllCards.removeAll(excludedCards)
    return when (mutableAllCards.size) {
        0 -> Card(Suit.SPADES, Rank.TWO)
        1 -> mutableAllCards.first()
        else -> {
            mutableAllCards.sortedBy { it.rank.power }; mutableAllCards.last()
        }
    }
}

private fun findSomeRanks(cardsSet: Set<Card>, rank: Rank, someCount:Int):Boolean{
    var count = 0
    for( it in cardsSet){
        if (it.rank == rank) count++
    }
    return count == someCount
}
