package io.mbition.github.ui.activities

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.WindowManager
import io.mbition.github.R
import io.mbition.github.USER_ID
import io.mbition.github.component
import io.mbition.github.databinding.ActivityUserDetailsBinding
import io.mbition.github.utils.DataBoundListAdapter
import io.mbition.github.utils.MarginDecoration
import io.mbition.github.utils.viewModelProvider
import io.mbition.githubui.user.UserReposViewHolder

/**
 * Created by Tohamy on 3/15/2018.
 */
class UserDetailsActivity : AppCompatActivity() {

    private val viewModel by viewModelProvider {
        component.userDetailsViewModel.also { it.getUserDetails(intent.getIntExtra(USER_ID, -1)) }
    }

    lateinit var activityUserDetailsBinding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        setSupportActionBar(activityUserDetailsBinding.header?.toolbar)
        setStatusBarColor()
        displayBackButton()
        val adapter = DataBoundListAdapter { UserReposViewHolder(it) }
        activityUserDetailsBinding.reposRecycler.adapter = adapter
        activityUserDetailsBinding.reposRecycler.addItemDecoration(MarginDecoration(this))
        viewModel.liveData.observe(this) {
            activityUserDetailsBinding.state = it
            activityUserDetailsBinding.executePendingBindings()
            adapter.replace(it.repos())
        }
        viewModel.uiActions.observe(this) {}
        activityUserDetailsBinding.viewModel = viewModel
    }

    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this@UserDetailsActivity, R.color.colorPrimaryDark)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    //display back button
    private fun displayBackButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

}