package br.com.atlas.extension

import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> ArrayList<*>.checkItemsAre() =
        if (all { it is T }) this as ArrayList<T>
        else null

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> Serializable.cast() = this as T
