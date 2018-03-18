package io.mbition.github.deps.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.mbition.github.AppControl
import io.mbition.github.BuildConfig
import io.mbition.github.deps.factories.NetworkingFactory.createService
import io.mbition.github.ui.activities.io.mbition.github.networking.NetworkService
import io.mbition.github.utils.AndroidCoroutines
import io.mbition.github.utils.Coroutines
import okhttp3.HttpUrl
import javax.inject.Singleton

/**
 * Created by Tohamy on 3/15/2018.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideContext(application: AppControl): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideNetworkService(): NetworkService =
            createService(BuildConfig.DEBUG, HttpUrl.parse("https://api.github.com/")!!)

    @Provides
    fun coroutines(): Coroutines = AndroidCoroutines()

}