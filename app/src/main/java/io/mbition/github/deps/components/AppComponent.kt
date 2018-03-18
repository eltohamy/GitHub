package io.mbition.github.deps.components

import dagger.BindsInstance
import dagger.Component
import io.mbition.github.AppControl
import io.mbition.github.NavigationControl
import io.mbition.github.deps.di.modules.AppModule
import io.mbition.github.ui.users.UsersViewModel
import io.mbition.githubui.user.UserDetailsViewModel
import javax.inject.Singleton

/**
 * Created by Tohamy on 3/15/2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    val navigationControl: NavigationControl

    val usersViewModel: UsersViewModel

    val userDetailsViewModel: UserDetailsViewModel

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AppControl): Builder

        fun build(): AppComponent
    }

    fun inject(app: AppControl)

}