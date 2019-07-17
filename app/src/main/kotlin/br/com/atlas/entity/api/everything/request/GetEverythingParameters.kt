package br.com.atlas.entity.api.everything.request

import br.com.atlas.entity.api.everything.sort.EverythingSortBy

/**
 * Created by rodrigosimoesrosa on 2019-07-12.
 * Copyright © 2019. All rights reserved.
 */
data class GetEverythingParameters(val keyword: String,
                                   val language: String,
                                   val from: String,
                                   val to: String,
                                   val page: Int? = null,
                                   val pageSize: Int? = 20,
                                   val sortBy: String? =
                                       EverythingSortBy.getDefault().getValue())
