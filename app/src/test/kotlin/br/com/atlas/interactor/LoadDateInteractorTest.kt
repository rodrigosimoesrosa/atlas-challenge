package br.com.atlas.interactor

import br.com.atlas.extension.toDefault
import br.com.atlas.interactor.date.DateInteractor
import br.com.atlas.interactor.date.LoadDateInteractor
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@RunWith(MockitoJUnitRunner::class)
class LoadDateInteractorTest {

    private lateinit var interactor: DateInteractor

    @Before
    fun setup() {
        interactor = LoadDateInteractor()
    }

    @Test
    fun `check getTo()`() {
        val toDateExpected = Calendar.getInstance().toDefault()
        val toDate = interactor.getTo()
        assertEquals(toDateExpected, toDate)
    }

    @Test
    fun `check getFrom()`() {
        val fromDateExpected = Calendar.getInstance()
        fromDateExpected.add(Calendar.DATE, -20)
        val fromDate = interactor.getFrom(-20)
        assertEquals(fromDateExpected.toDefault(), fromDate)
    }

}
