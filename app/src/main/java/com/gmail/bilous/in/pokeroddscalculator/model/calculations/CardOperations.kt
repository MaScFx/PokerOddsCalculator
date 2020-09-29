package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Rank
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.responses.FlushResponse
import java.lang.IllegalArgumentException

fun findStraightInSet(cardsSet: Set<Card>): Boolean {
    if (cardsSet.size < 5) return false
    val cardsPowerList = ArrayList<Int>()
    if (cardsSet.contains(Card(Suit.SPADES, Rank.ACE)) || cardsSet.contains( Card(Suit.DIAMONDS, Rank.ACE)) ||
        cardsSet.contains(Card(Suit.HEARTS, Rank.ACE)) || cardsSet.contains( Card(Suit.CLUBS, Rank.ACE))) {
        cardsPowerList.add(1)
    }
    cardsSet.forEach { cardsPowerList.add(it.rank.power) }
    cardsPowerList.sortedBy { it }

    var sequenceCount = 1
    var previousRank = cardsPowerList[0]
    cardsPowerList.forEach {
        if (it - previousRank <= 1) sequenceCount++
        else sequenceCount = 1
        previousRank = it
        if (sequenceCount >= 5) return true
    }
    return false
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
    return when {
        diamondsSet.size >= 4 -> FlushResponse(true, removeLowPowerCardsInFlush(diamondsSet))
        clubsSet.size >= 4 -> FlushResponse(true, removeLowPowerCardsInFlush(clubsSet))
        heartsSet.size >= 4 -> FlushResponse(true, removeLowPowerCardsInFlush(heartsSet))
        spadesSet.size >= 4 -> FlushResponse(true, removeLowPowerCardsInFlush(spadesSet))
        else -> FlushResponse(false)
    }
}

fun findFourOfAKindInSet(cardsSet: Set<Card>):Boolean{
    for (r in Rank.values()){
        if (findFourRanks(cardsSet,r)) return true
    }
    return false

}

private fun findFourRanks(cardsSet: Set<Card>, rank: Rank):Boolean{
    var count = 0
    for( it in cardsSet){
        if (it.rank == rank) count++
    }
    return count >= 4
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


//
//fun findFlushFromList(cardsList: List<Card>): Boolean {
//    if (cardsList.size < 4) return false
//    var diamondsCount = 0
//    var clubsCount = 0
//    var heartsCount = 0
//    var spadesCount = 0
//    cardsList.forEach {
//        if (Suit.DIAMONDS == it.suit) diamondsCount++
//        if (Suit.CLUBS == it.suit) clubsCount++
//        if (Suit.HEARTS == it.suit) heartsCount++
//        if (Suit.SPADES == it.suit) spadesCount++
//    }
//    return (diamondsCount >= 4 || clubsCount >= 4 || heartsCount >= 4 || spadesCount >= 4)
//}