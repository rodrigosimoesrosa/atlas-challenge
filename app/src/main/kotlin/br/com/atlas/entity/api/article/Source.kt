package br.com.atlas.entity.api.article

import br.com.atlas.base.entity.DomainMappable
import br.com.atlas.entity.domain.everything.Source
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
data class Source(@SerializedName("id")
                  @Expose
                  val id: String? = null,
                  @SerializedName("name")
                  @Expose
                  val name: String? = null) : Serializable, DomainMappable<Source> {

    override fun toDomain(): Source {
        return Source(id = id, name = name)
    }

}
