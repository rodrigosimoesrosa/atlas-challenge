package br.com.atlas.di.module

import br.com.atlas.interactor.date.DateInteractor
import br.com.atlas.interactor.date.LoadDateInteractor
import br.com.atlas.interactor.everything.LoadEverythingInteractor
import br.com.atlas.interactor.everything.NewsInteractor
import dagger.Binds
import dagger.Module

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Module
abstract class InteractorModule {

    @Binds
    abstract fun bindLoadEverythingInteractor(interactor: LoadEverythingInteractor): NewsInteractor.LoadEverything

    @Binds
    abstract fun bindLoadDateInteractor(interactor: LoadDateInteractor): DateInteractor

}