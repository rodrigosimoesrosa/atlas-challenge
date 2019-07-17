package br.com.atlas.di.module

import br.com.atlas.repository.everything.EverythingRepository
import br.com.atlas.repository.everything.GetEverythingRepository
import dagger.Binds
import dagger.Module

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindGetEverythingRepository(repository: GetEverythingRepository): EverythingRepository.Get
}