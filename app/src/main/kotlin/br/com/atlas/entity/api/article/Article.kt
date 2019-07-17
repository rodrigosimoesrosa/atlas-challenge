package br.com.atlas.entity.api.article

import br.com.atlas.base.entity.DomainMappable
import br.com.atlas.entity.domain.everything.Article
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
data class Article(@SerializedName("source")
                   @Expose
                   val source: Source? = null,
                   @SerializedName("author")
                   @Expose
                   val author: String? = null,
                   @SerializedName("title")
                   @Expose
                   val title: String? = null,
                   @SerializedName("description")
                   @Expose
                   val description: String? = null,
                   @SerializedName("url")
                   @Expose
                   val url: String? = null,
                   @SerializedName("urlToImage")
                   @Expose
                   val urlToImage: String? = null,
                   @SerializedName("publishedAt")
                   @Expose
                   val publishedAt: String? = null,
                   @SerializedName("content")
                   @Expose
                   val content: String? = null) : Serializable, DomainMappable<Article> {

    override fun toDomain(): Article {
        return Article(
            source = source?.toDomain(),
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content)
    }

}