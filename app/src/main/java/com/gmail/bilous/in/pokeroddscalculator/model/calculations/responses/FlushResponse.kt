package com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.responses

import com.gmail.bilous.`in`.pokeroddscalculator.model.base.Card
import java.util.*

class FlushResponse(var isFlush:Boolean, val flushSet: TreeSet<Card> = TreeSet<Card>()) {
}