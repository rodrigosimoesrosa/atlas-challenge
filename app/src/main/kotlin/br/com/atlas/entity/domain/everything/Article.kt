package br.com.atlas.entity.domain.everything

import br.com.atlas.entity.view.everything.Article
import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
data class Article(val source: Source? = null,
                   val author: String? = null,
                   val title: String? = null,
                   val description: String? = null,
                   val url: String? = null,
                   val urlToImage: String? = null,
                   val publishedAt: String? = null,
                   val content: String? = null) : Serializable {

    fun toView(): Article {
        return Article(
                source = source?.toView(),
                author = author,
                title = title,
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt,
                content = content)
    }
}