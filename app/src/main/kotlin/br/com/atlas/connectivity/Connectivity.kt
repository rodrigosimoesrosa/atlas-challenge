package br.com.atlas.connectivity

import br.com.atlas.AtlasApplication
import br.com.atlas.R
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
object Connectivity {

    fun checkInternetNotFoundException(throwable: Throwable?): InternetNotFoundException? {
        if (throwable == null) {
            return null
        }

        when (throwable) {
            is SocketTimeoutException,
            is UnknownHostException,
            is ConnectException,
            is TimeoutException -> {
                return InternetNotFoundException()
            }
        }

        return null
    }

    class InternetNotFoundException : Exception(
        AtlasApplication.getContext().getString(R.string.internet_not_found) ?: "")
}
