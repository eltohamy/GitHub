package io.mbition.github.repos

import io.mbition.github.models.Repo
import io.mbition.github.models.UserData
import io.mbition.github.ui.activities.io.mbition.github.networking.NetworkService
import javax.inject.Inject

/**
 * Created by Tohamy on 3/15/2018.
 */
class UserDetailsRepo
@Inject constructor(private val networkService: NetworkService) {

    suspend fun getUserDetails(userId: Int): UserData = networkService.getUserDetails(userId).await()

    suspend fun getRepos(userId: Int): List<Repo> = networkService.getRepos(userId).await()
}