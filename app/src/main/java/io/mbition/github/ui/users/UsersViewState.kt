package io.mbition.github.ui.users

import io.mbition.github.models.Resource
import io.mbition.github.models.User
import io.mbition.github.models.orElse

/**
 * Created by Tohamy on 3/15/2018.
 */
data class UsersViewState(val usersData: Resource<UsersData>) {
    fun users(): List<User> = usersData.map { it.users }.orElse(emptyList<User>())

}