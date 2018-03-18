package io.mbition.githubui.user

import io.mbition.github.models.Repo
import io.mbition.github.models.Resource
import io.mbition.github.models.UserData
import io.mbition.github.models.orElse

/**
 * Created by Tohamy on 3/15/2018.
 */
data class UserDetailsViewState(val userDetails: Resource<UserDetails>) {
    fun repos(): List<Repo> = userDetails.map { it.repos }.orElse(emptyList())

    fun user(): UserData? = userDetails.map { it.user }.orElse(null)
}
