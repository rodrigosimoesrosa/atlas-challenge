package br.com.atlas.di.module

import android.app.Application
import br.com.atlas.AtlasApplication
import br.com.atlas.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Module
class ApplicationModule(private val myApplication: AtlasApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return myApplication
    }

}