package br.com.atlas.interactor.everything

import br.com.atlas.base.reactive.SingleExecutor
import br.com.atlas.entity.api.everything.request.GetEverythingParameters
import br.com.atlas.entity.domain.everything.Article
import br.com.atlas.extension.listToDomain
import br.com.atlas.repository.everything.EverythingRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
class LoadEverythingInteractor @Inject
constructor(private val repository: EverythingRepository.Get,
            @Named("ioScheduler") val io: Scheduler,
            @Named("mainThreadScheduler") val ui: Scheduler): SingleExecutor<List<Article>,
        GetEverythingParameters>(io, ui), NewsInteractor.LoadEverything {

    override fun loadEverything(keyword: String,
                                total: Int,
                                fromDate: String,
                                toDate: String,
                                language: String,
                                listener: NewsInteractor.OnLoadEverything) {
        val parameters = GetEverythingParameters(keyword = keyword,
                from = fromDate, to = toDate, language = language, pageSize = total)

        execute(listener, parameters)
    }

    override fun buildSingle(params: GetEverythingParameters?): Single<List<Article>> {
        if (params == null) {
            throw Exception()
        }

        return repository.getEverything(params).listToDomain()
    }

    override fun unregister() {
        disposables.clear()
    }

}
