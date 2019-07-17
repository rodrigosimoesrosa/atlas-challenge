package br.com.atlas.di.component

import br.com.atlas.di.module.InteractorModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Component(modules = [InteractorModule::class])
@Singleton
interface ViewComponent
