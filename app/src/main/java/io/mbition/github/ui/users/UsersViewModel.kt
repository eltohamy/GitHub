package io.mbition.github.ui.users

import android.arch.lifecycle.ViewModel
import io.mbition.github.NavigationControl
import io.mbition.github.models.Resource
import io.mbition.github.repos.UsersRepo
import io.mbition.github.utils.Coroutines
import io.mbition.github.utils.LiveDataDelegate
import io.mbition.github.utils.UiActionsLiveData
import javax.inject.Inject

/**
 * Created by Tohamy on 3/15/2018.
 */
class UsersViewModel @Inject constructor(private val usersRepo: UsersRepo,
                                         private val navigationControl: NavigationControl,
                                         private val coroutines: Coroutines
) : ViewModel() {

    val liveData = LiveDataDelegate(UsersViewState(Resource.Empty))

    private var state by liveData

    val uiActions = UiActionsLiveData()

    fun loadUsers() = coroutines {
        state = state.copy(usersData = Resource.Loading)
        state = try {
            val users = usersRepo.loadUsers()
            state.copy(usersData = Resource.Success(users))
        } catch (e: Exception) {
            state.copy(usersData = Resource.Error(e))
        }
    }

    fun openUserDetails(userId: Int) =
            uiActions { navigationControl.navigateToUserDetails(it, userId) }
}