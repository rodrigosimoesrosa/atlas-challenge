package br.com.atlas.entity.api.everything.sort

/**
 * Created by rodrigosimoesrosa on 2019-07-12.
 * Copyright © 2019. All rights reserved.
 */
enum class EverythingSortBy(private val value: String) {
    RELEVANCY("relevancy"),
    POPULARITY("popularity"),
    PUBLISHED_AT("publishedAt");

    companion object {
        fun getDefault() = PUBLISHED_AT
    }

    fun getValue() = value
}
