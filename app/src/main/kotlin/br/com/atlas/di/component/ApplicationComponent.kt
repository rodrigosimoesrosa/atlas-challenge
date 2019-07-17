package br.com.atlas.di.component

import android.app.Application
import br.com.atlas.AtlasApplication
import br.com.atlas.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
@Component(modules = [
    APIModule::class,
    SchedulerModule::class,
    RouterModule::class,
    PresenterModule::class,
    RepositoryModule::class,
    InteractorModule::class,
    ActivitiesModule::class,
    AndroidSupportInjectionModule::class])
@Singleton
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: APIModule): Builder

        @BindsInstance
        fun scheduleModule(schedulerModule: SchedulerModule): Builder

        fun build(): ApplicationComponent
    }

    fun inject(appController: AtlasApplication)
}
