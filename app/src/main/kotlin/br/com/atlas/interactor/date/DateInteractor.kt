package br.com.atlas.interactor.date

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
interface DateInteractor {

    fun getFrom(days: Int): String
    fun getTo(): String

}
