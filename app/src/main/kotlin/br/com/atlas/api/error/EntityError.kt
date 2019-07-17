package br.com.atlas.api.error

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
interface EntityError {
    var message: String?

    fun toThrowable(): Throwable {
        return Throwable(message)
    }
}
