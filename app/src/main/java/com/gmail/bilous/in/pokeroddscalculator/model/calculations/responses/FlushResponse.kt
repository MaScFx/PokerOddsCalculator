package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.responses

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card

class FlushResponse(var isFlush:Boolean, val setFlush:Set<Card> = setOf()) {
}