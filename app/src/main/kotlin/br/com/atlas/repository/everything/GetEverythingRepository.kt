package br.com.atlas.repository.everything

import br.com.atlas.AtlasApplication
import br.com.atlas.BuildConfig
import br.com.atlas.api.domain.everything.EverythingAPI
import br.com.atlas.entity.api.article.Article
import br.com.atlas.entity.api.everything.error.GetEverythingError
import br.com.atlas.entity.api.everything.request.GetEverythingParameters
import br.com.atlas.extension.mapErrorByClient
import br.com.atlas.extension.mapNetworkErrors
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
class GetEverythingRepository @Inject constructor(val api: EverythingAPI.Methods) :
    EverythingRepository.Get {

    override fun getEverything(parameters: GetEverythingParameters): Single<List<Article>> {
        return api.getEverything(
            apiKey = BuildConfig.API_KEY,
            keyword = parameters.keyword,
            language = parameters.language,
            from = parameters.from,
            to = parameters.to,
            page = parameters.page,
            pageSize = parameters.pageSize,
            sortBy = parameters.sortBy)
            .mapNetworkErrors()
            .mapErrorByClient(GetEverythingError(AtlasApplication.getContext()))
            .map { it.articles }
    }

}
