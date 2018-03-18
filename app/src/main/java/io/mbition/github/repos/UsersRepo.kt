package io.mbition.github.repos

import io.mbition.github.models.User
import io.mbition.github.ui.activities.io.mbition.github.networking.NetworkService
import io.mbition.github.ui.users.UsersData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Tohamy on 3/15/2018.
 */
@Singleton
class UsersRepo @Inject constructor(private val networkService: NetworkService) {

    suspend fun getUsers(): List<User> = networkService.getUsers().await()

    suspend fun loadUsers(): UsersData {
        return UsersData(getUsers())
    }

}