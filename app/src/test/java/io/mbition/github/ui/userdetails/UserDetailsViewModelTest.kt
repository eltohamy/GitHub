package io.mbition.github.ui.userdetails

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.v4.app.FragmentActivity
import com.nhaarman.mockito_kotlin.mock
import io.mbition.github.repos.UserDetailsRepo
import io.mbition.github.utils.TestCoroutines
import io.mbition.github.utils.TestData.REPO_1
import io.mbition.github.utils.TestData.REPO_2
import io.mbition.github.utils.TestData.REPO_3
import io.mbition.github.utils.TestData.REPO_4
import io.mbition.github.utils.TestData.USER_DATA
import io.mbition.github.utils.shouldContain
import io.mbition.github.utils.willReturn
import io.mbition.githubui.user.UserDetailsViewModel
import io.mbition.githubui.user.UserDetailsViewState
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Tohamy on 3/15/2018.
 */
class UserDetailsViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val userDetailsRepo: UserDetailsRepo = mock()
    val activity: FragmentActivity = mock()
    val userDetailsViewModel by lazy { UserDetailsViewModel(userDetailsRepo, TestCoroutines()) }

    val states = mutableListOf<UserDetailsViewState>()

    @Before
    fun setUp() {
        userDetailsViewModel.liveData.observeForever({ states.add(it) })
        userDetailsViewModel.uiActions.observeForever({ it(activity) })
    }

    @Test
    fun getUserDetails() {
        runBlocking {
            userDetailsRepo.getUserDetails(USER_ID) willReturn USER_DATA
            userDetailsRepo.getRepos(USER_ID) willReturn listOf(REPO_1, REPO_2, REPO_3, REPO_4)
        }

        userDetailsViewModel.getUserDetails(USER_ID)


        states.map { it.userDetails } shouldContain {
            empty().loading().success()
        }
    }

    companion object {
        private val USER_ID = 1
    }
}