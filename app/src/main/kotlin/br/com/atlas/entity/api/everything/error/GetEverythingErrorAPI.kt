package br.com.atlas.entity.api.everything.error

import br.com.atlas.api.error.EntityError
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
data class GetEverythingErrorAPI(@SerializedName("status")
                                 @Expose
                                 private val status: String,
                                 @SerializedName("code")
                                 @Expose
                                 private val code: String,
                                 @SerializedName("message")
                                 @Expose
                                 override var message: String?) : EntityError, Serializable
