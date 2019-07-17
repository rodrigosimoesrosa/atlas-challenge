package br.com.atlas.di.module

import br.com.atlas.api.APIProvider
import br.com.atlas.api.domain.everything.EverythingAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Module
class APIModule {

    @Provides
    @Singleton
    fun provideProductAPI(): EverythingAPI.Methods {
        return APIProvider.getAPI(EverythingAPI.Methods::class.java).getAPI()
    }

}