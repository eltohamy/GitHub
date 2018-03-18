package io.mbition.githubui.user

import android.arch.lifecycle.ViewModel
import io.mbition.github.models.Resource
import io.mbition.github.repos.UserDetailsRepo
import io.mbition.github.utils.Coroutines
import io.mbition.github.utils.LiveDataDelegate
import io.mbition.github.utils.UiActionsLiveData
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

/**
 * Created by Tohamy on 3/15/2018.
 */
class UserDetailsViewModel
@Inject constructor(
        private val userDetailsRepo: UserDetailsRepo,
        private val coroutines: Coroutines
) : ViewModel() {

    val liveData = LiveDataDelegate(UserDetailsViewState(Resource.Empty))

    private var state by liveData

    val uiActions = UiActionsLiveData()

    fun getUserDetails(login: Int) = coroutines {
        state = state.copy(userDetails = Resource.Loading)

        state = try {
            val userDeferred = async { userDetailsRepo.getUserDetails(login) }
            val reposDeferred = async { userDetailsRepo.getRepos(login) }
            val detail = UserDetails(userDeferred.await(), reposDeferred.await())
            state.copy(userDetails = Resource.Success(detail))
        } catch (e: Exception) {
            state.copy(userDetails = Resource.Error(e))
        }
    }
}
