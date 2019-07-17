package br.com.atlas.entity.view.everything

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Parcelize
data class Article(val source: Source? = null,
                   val author: String? = null,
                   val title: String? = null,
                   val description: String? = null,
                   val url: String? = null,
                   val urlToImage: String? = null,
                   val publishedAt: String? = null,
                   val content: String? = null) : Serializable, Parcelable