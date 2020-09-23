package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Ranks
import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Suit

fun getFullDeck(): HashSet<Card> {
    val fullDeck = HashSet<Card>()
    Ranks.values().forEach { it ->
        fullDeck.add(Card(Suit.CLUBS, it))
        fullDeck.add(Card(Suit.SPADES, it))
        fullDeck.add(Card(Suit.DIAMONDS, it))
        fullDeck.add(Card(Suit.HEARTS, it))}
    return fullDeck
}

