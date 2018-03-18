package io.mbition.github.utils

import io.mbition.github.models.Repo
import io.mbition.github.models.User
import io.mbition.github.models.UserData

/**
 * Created by Tohamy on 3/15/2018.
 */
object TestData {
    val USER_1 = User("gists_url", "repos_url", "following_url", "starred_url", "login",
            "followers_url", "type", "url", "subscriptions_url", "received_events_url",
            "avatar_url", "events_url", "html_url", false, 123, "gravatar_id", "organizations_url")
    val USER_2 = User("gists_url", "repos_url", "following_url", "starred_url", "login",
            "followers_url", "type", "url", "subscriptions_url", "received_events_url",
            "avatar_url", "events_url", "html_url", false, 123, "gravatar_id", "organizations_url")
    val USER_3 = User("gists_url", "repos_url", "following_url", "starred_url", "login",
            "followers_url", "type", "url", "subscriptions_url", "received_events_url",
            "avatar_url", "events_url", "html_url", false, 123, "gravatar_id", "organizations_url")
    val USER_4 = User("gists_url", "repos_url", "following_url", "starred_url", "login",
            "followers_url", "type", "url", "subscriptions_url", "received_events_url",
            "avatar_url", "events_url", "html_url", false, 123, "gravatar_id", "organizations_url")
    val USER_DATA = UserData("gists_url", "repos_url", "following_url")
    val REPO_1 = Repo(1, "pushed_at", "subscription_url", "bio")
    val REPO_2 = Repo(2, "pushed_at", "subscription_url", "bio")
    val REPO_3 = Repo(3, "pushed_at", "subscription_url", "bio")
    val REPO_4 = Repo(4, "pushed_at", "subscription_url", "bio")

}
