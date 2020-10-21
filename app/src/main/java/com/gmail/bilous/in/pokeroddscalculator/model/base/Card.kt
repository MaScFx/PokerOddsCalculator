package com.gmail.bilous.`in`.pokeroddscalculator.model.base

class Card(var suit: Suit, var rank: Rank) : Comparable<Card> {
    override fun compareTo(other: Card): Int {
        val thisCardRank = this.rank.power * 10 + this.suit.rankForComparable
        val otherCardRank = other.rank.power * 10 + other.suit.rankForComparable
        return thisCardRank - otherCardRank
    }

}