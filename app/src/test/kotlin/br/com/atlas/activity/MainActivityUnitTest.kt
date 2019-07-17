package br.com.atlas.activity

import android.os.Bundle
import br.com.atlas.ui.main.MainActivity
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

/**
 * Created by rodrigosimoesrosa on 2019-06-26.
 * Copyright © 2019. All rights reserved.
 */
@RunWith(RobolectricTestRunner::class)
class MainActivityUnitTest {

    private lateinit var activity: MainActivity
    private lateinit var controller: ActivityController<MainActivity>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        controller = Robolectric.buildActivity(MainActivity::class.java)
        activity = controller.create().start().get()
    }

    @Test
    fun `check recreate Activity`() {
        val bundle = Bundle()

        controller
            .saveInstanceState(bundle)
            .pause()
            .stop()
            .destroy()

        controller = Robolectric.buildActivity(MainActivity::class.java)
            .create(bundle)
            .start()
            .restoreInstanceState(bundle)
            .resume()
            .visible()

        activity = controller.get()
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }
}