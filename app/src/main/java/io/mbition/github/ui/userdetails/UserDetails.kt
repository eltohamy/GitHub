package io.mbition.githubui.user

import io.mbition.github.models.Repo
import io.mbition.github.models.UserData

/**
 * Created by Tohamy on 3/15/2018.
 */
data class UserDetails(val user: UserData, val repos: List<Repo>)