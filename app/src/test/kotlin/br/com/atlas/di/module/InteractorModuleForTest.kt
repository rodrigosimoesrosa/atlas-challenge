package br.com.atlas.di.module

import br.com.atlas.interactor.date.DateInteractor
import br.com.atlas.interactor.date.LoadDateInteractor
import br.com.atlas.interactor.everything.NewsInteractor
import com.nhaarman.mockito_kotlin.mock
import dagger.Module

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Module
class InteractorModuleForTest {

    companion object {
        val newInteractor: NewsInteractor.LoadEverything = mock()
        val dateInteractor: DateInteractor = LoadDateInteractor()
    }

}