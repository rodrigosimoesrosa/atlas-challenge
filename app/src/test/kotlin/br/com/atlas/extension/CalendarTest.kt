package br.com.atlas.extension

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@RunWith(MockitoJUnitRunner::class)
class CalendarTest {

    private val apiDate = "2019-07-16T20:00:50Z"

    @Test
    fun `check api date to ISO8601UTC EN`() {
        assertEquals("7/16/19", apiDate.toISO8601UTC().byLocale())
    }

    @Test
    fun `check api date to ISO8601UTC BR`() {
        val br = Locale("pt", "BR")
        assertEquals("16/07/19", apiDate.toISO8601UTC(br).byLocale(br))
    }

}