package io.mbition.github

import android.content.Intent
import android.support.v4.app.FragmentActivity
import io.mbition.github.ui.activities.HomeActivity
import io.mbition.github.ui.activities.UserDetailsActivity
import io.mbition.github.ui.users.UsersFragment
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Tohamy on 3/15/2018.
 */

const val USER_ID = "USER_ID"

@Singleton
class NavigationControl @Inject constructor() {


    fun navigateToHome(activity: FragmentActivity) {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    fun navigateToUsers(activity: FragmentActivity) {
        val tag = UsersFragment::class.java.simpleName;
        val usersFragment = UsersFragment.newInstance()
        activity.supportFragmentManager.beginTransaction()
                .replace(R.id.main_body, usersFragment, tag)
                .commitAllowingStateLoss()
    }

    fun navigateToUserDetails(activity: FragmentActivity, id: Int) {
        val intent = Intent(activity, UserDetailsActivity::class.java).apply {
            putExtra(USER_ID, id)
        }
        activity.startActivity(intent)
    }
}