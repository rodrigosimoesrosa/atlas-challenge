package br.com.atlas.entity.domain.everything

import br.com.atlas.entity.view.everything.Source
import java.io.Serializable

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
data class Source(val id: String? = null,
                  val name: String? = null) : Serializable {

    fun toView(): Source? {
        return Source(id = id, name = name)
    }
}
