package com.abyat.service

import org.junit.Assert.*
import org.junit.Test

class MVPCalculatorTest {
    @Test
    fun testFindMVP() {
        val mvp = MVPCalculator.findMVP("files/")
        assertEquals(mvp, "nick3")
    }
}
