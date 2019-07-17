package br.com.atlas.di

import br.com.atlas.di.module.InteractorModuleForTest
import br.com.atlas.di.module.PresenterModuleForTest
import br.com.atlas.di.module.RouterModuleForTest
import br.com.atlas.presenter.ListNewsPresenterTest
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-06-26.
 * Copyright © 2019. All rights reserved.
 */
@Singleton
@Component(modules = [
    PresenterModuleForTest::class,
    RouterModuleForTest::class,
    InteractorModuleForTest::class])
interface ListNewsComponentForTest {

    fun inject(presenter: ListNewsPresenterTest)

}
