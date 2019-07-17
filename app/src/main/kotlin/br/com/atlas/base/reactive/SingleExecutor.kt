package br.com.atlas.base.reactive

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
abstract class SingleExecutor<TYPE, in PARAMS>(private val ioScheduler: Scheduler,
                                               private val uiScheduler: Scheduler) {

    protected val disposables: CompositeDisposable = CompositeDisposable()
    private var listener: SingleExecutorListener? = null

    abstract fun buildSingle(params: PARAMS?): Single<TYPE>

    @Suppress("UNCHECKED_CAST")
    fun execute(observer: Any, params: PARAMS? = null) {
        if (observer !is SingleObserver<*>) {
            throw Exception("This object wasn't SingleObserver type!")
        }

        val singleObserver: SingleObserver<TYPE> = observer as SingleObserver<TYPE>
        val observable: Single<TYPE> = buildSingle(params)
            .doOnSubscribe { listener?.begin() }
            .doFinally { listener?.finally() }
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)

        (observable.subscribeWith(singleObserver) as? Disposable)?.let { disposables.add(it) }
    }

    fun setListener(listener: SingleExecutorListener) {
        this.listener = listener
    }

    fun dispose() {
        disposables.clear()
    }

    interface SingleExecutorListener {
        fun begin()
        fun finally()
    }
}
