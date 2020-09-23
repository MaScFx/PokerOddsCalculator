package com.gmail.bilous.`in`.pokeroddscalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.bilous.`in`.pokeroddscalculator.R
import com.gmail.bilous.`in`.pokeroddscalculator.model.calculations.getFullDeck

class MainActivity : AppCompatActivity() {
    public final val TAG: String = "MyLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var a = getFullDeck()
        var i = 0
        a.iterator().forEach { it ->
            i++
            Log.i(TAG, "count =$i onCreate: $it")
        }
    }
}