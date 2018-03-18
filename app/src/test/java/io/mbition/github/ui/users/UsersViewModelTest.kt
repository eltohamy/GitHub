package io.mbition.github.ui.users

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.v4.app.FragmentActivity
import com.nhaarman.mockito_kotlin.mock
import io.mbition.github.NavigationControl
import io.mbition.github.repos.UsersRepo
import io.mbition.github.utils.TestCoroutines
import io.mbition.github.utils.TestData.USER_1
import io.mbition.github.utils.TestData.USER_2
import io.mbition.github.utils.TestData.USER_3
import io.mbition.github.utils.TestData.USER_4
import io.mbition.github.utils.shouldContain
import io.mbition.github.utils.willReturn
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Tohamy on 3/15/2018.
 */
class UsersViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val usersRepo: UsersRepo = mock()

    val navigationController: NavigationControl = mock()

    val activity: FragmentActivity = mock()

    val repoViewModel by lazy { UsersViewModel(usersRepo, navigationController, TestCoroutines()) }

    val states = mutableListOf<UsersViewState>()

    @Before
    fun setUp() {
        repoViewModel.liveData.observeForever({ states.add(it) })
        repoViewModel.uiActions.observeForever({ it(activity) })
    }

    @Test
    fun loadUsers() {
        runBlocking {
            usersRepo.getUsers() willReturn listOf(USER_1, USER_2, USER_3, USER_4)
        }

        repoViewModel.loadUsers()

        states.map { it.usersData } shouldContain {
            empty().loading().success()
        }
    }
}