package io.mbition.github.ui.activities.io.mbition.github.networking

import io.mbition.github.models.Repo
import io.mbition.github.models.User
import io.mbition.github.models.UserData
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Tohamy on 3/15/2018.
 */
interface NetworkService {

    @GET("users")
    fun getUsers(): Deferred<List<User>>

    @GET("users/{userId}")
    fun getUserDetails(@Path("userId") userId: Int): Deferred<UserData>

    @GET("users/{userId}/repos")
    fun getRepos(@Path("userId") userId: Int): Deferred<List<Repo>>

}