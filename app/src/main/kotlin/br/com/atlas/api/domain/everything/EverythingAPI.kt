package br.com.atlas.api.domain.everything

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
import br.com.atlas.entity.api.everything.GetEverything
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

object EverythingAPI {

    const val GET_EVERYTHING = "everything"

    object Parameters {
        const val KEYWORD = "q"
        const val API_KEY = "apiKey"

        const val FROM = "from"
        const val TO = "to"
        const val SORT_BY = "sortBy"
        const val PAGE = "page"
        const val PAGE_SIZE = "pageSize"
        const val LANGUAGE = "language"
    }

    interface Methods {

        @GET(GET_EVERYTHING)
        fun getEverything(@Query(Parameters.API_KEY) apiKey: String,
                          @Query(Parameters.KEYWORD) keyword: String? = null,
                          @Query(Parameters.FROM) from: String? = null,
                          @Query(Parameters.TO) to: String? = null,
                          @Query(Parameters.SORT_BY) sortBy: String? = null,
                          @Query(Parameters.PAGE) page: Int? = null,
                          @Query(Parameters.PAGE_SIZE) pageSize: Int? = null,
                          @Query(Parameters.LANGUAGE) language: String? = null): Single<GetEverything>
    }
}
