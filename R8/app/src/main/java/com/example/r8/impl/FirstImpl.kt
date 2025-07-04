package com.example.r8.impl

import com.example.r8.inter.FakeInterface

class FirstImpl :FakeInterface{
    override fun getCustomMessage(): String {
     return "First implementation"
    }
}