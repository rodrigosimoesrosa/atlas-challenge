package br.com.atlas.entity.api.everything.error

import android.content.Context
import br.com.atlas.R
import br.com.atlas.api.error.EntityError

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
class GetEverythingError(context: Context, override var message: String? =
    context.getString(R.string.get_everything_error)) : EntityError
