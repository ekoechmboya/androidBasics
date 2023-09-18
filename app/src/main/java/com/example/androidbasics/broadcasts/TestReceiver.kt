package com.example.androidbasics.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TestReceiver: BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1?.action == "TEST_ACTION") {
            println("Received test intent")
        }
    }
}