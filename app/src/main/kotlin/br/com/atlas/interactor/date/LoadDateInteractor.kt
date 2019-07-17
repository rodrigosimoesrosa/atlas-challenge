package br.com.atlas.interactor.date

import br.com.atlas.extension.toDefault
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
class LoadDateInteractor @Inject constructor(): DateInteractor {

    private var now: Calendar? = null

    private fun checkIfExistInstance() {
        if (now == null) {
            now = Calendar.getInstance()
        }
    }

    override fun getFrom(days: Int): String {
        checkIfExistInstance()

        val fromDate = Calendar.getInstance()
        fromDate.time = now?.time
        fromDate.add(Calendar.DATE, days)

        return fromDate.toDefault()
    }

    override fun getTo(): String {
        checkIfExistInstance()

        val toDate = Calendar.getInstance()
        toDate.time = now?.time

        return toDate.toDefault()
    }

}
