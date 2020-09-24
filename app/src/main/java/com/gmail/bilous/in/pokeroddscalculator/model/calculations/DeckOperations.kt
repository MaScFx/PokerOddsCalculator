package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Rank
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit

fun getFullDeck(): HashSet<Card> {
    val fullDeck = HashSet<Card>()
    Rank.values().forEach { it ->
        fullDeck.add(Card(Suit.CLUBS, it))
        fullDeck.add(Card(Suit.SPADES, it))
        fullDeck.add(Card(Suit.DIAMONDS, it))
        fullDeck.add(Card(Suit.HEARTS, it))}
    return fullDeck
}

fun getRandomCardFromDeckAndDeleteFromSet(cardSet:MutableSet<Card>):Card{
    val card=cardSet.random()
    cardSet.remove(card)
    return card
}

