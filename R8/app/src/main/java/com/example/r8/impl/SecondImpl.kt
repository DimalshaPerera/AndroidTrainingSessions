package com.example.r8.impl

import com.example.r8.inter.FakeInterface

class SecondImpl :FakeInterface {
    override fun getCustomMessage(): String {
        return "Second impl"
    }
}