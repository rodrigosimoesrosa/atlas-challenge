package br.com.atlas

import android.app.Activity
import android.app.Application
import android.content.Context
import br.com.atlas.di.component.DaggerApplicationComponent
import br.com.atlas.di.module.APIModule
import br.com.atlas.di.module.SchedulerModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
class AtlasApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    companion object {
        var instance: AtlasApplication? = null
        fun getContext() = instance as Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        DaggerApplicationComponent
                .builder()
                .application(this)
                .apiModule(APIModule())
                .scheduleModule(SchedulerModule())
                .build()
                .inject(this)
    }
}
