package br.com.atlas.entity.view.everything

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Parcelize
data class Source(val id: String? = null,
                  val name: String? = null) : Serializable, Parcelable
