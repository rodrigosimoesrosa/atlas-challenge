package br.com.atlas.entity.api.everything

import br.com.atlas.entity.api.article.Article
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
data class GetEverything(@SerializedName("status")
                         @Expose
                         val status: String? = null,
                         @SerializedName("totalResults")
                         @Expose
                         val totalResults: Int? = null,
                         @SerializedName("articles")
                         @Expose
                         val articles: List<Article>? = null) : Serializable