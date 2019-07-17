package br.com.atlas.base.reactive.interactor

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
abstract class OnSingleObserver<TYPE> : SingleObserver<TYPE> {

    private var disposable: Disposable? = null

    override fun onSubscribe(disposable: Disposable) {
        this.disposable = disposable
    }

}
