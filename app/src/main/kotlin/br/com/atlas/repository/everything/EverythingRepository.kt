package br.com.atlas.repository.everything

import br.com.atlas.entity.api.article.Article
import br.com.atlas.entity.api.everything.request.GetEverythingParameters
import io.reactivex.Single

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
object EverythingRepository {

    interface Get {
        fun getEverything(parameters: GetEverythingParameters): Single<List<Article>>
    }

}
