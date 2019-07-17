package br.com.atlas.interactor.everything

import br.com.atlas.base.interactor.Interactor
import br.com.atlas.entity.domain.everything.Article

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
object NewsInteractor {

    interface LoadEverything : Interactor.LifeCycle {
        fun loadEverything(keyword: String,
                           total: Int,
                           fromDate: String,
                           toDate: String,
                           language: String,
                           listener: OnLoadEverything)
    }

    interface OnLoadEverything {
        fun onSuccess(articles: List<Article>)
        fun onError(throwable: Throwable)
    }

}
