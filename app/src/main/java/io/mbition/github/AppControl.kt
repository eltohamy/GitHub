package io.mbition.github

import android.content.Context
import android.support.multidex.MultiDexApplication
import android.support.v4.app.Fragment
import io.mbition.github.deps.components.AppComponent
import io.mbition.github.deps.components.DaggerAppComponent

/**
 * Created by Tohamy on 3/15/2018.
 */
class AppControl : MultiDexApplication() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().application(this).build()
    }
}

val Context.component: AppComponent
    get() = (applicationContext as AppControl).component

val Fragment.component: AppComponent
    get() = activity!!.component