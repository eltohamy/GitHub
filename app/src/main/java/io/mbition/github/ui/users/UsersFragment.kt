package io.mbition.github.ui.users

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.mbition.github.R
import io.mbition.github.component
import io.mbition.github.databinding.UsersFragmentBinding
import io.mbition.github.utils.DataBoundListAdapter
import io.mbition.github.utils.MarginDecoration
import io.mbition.github.utils.viewModelProvider

/**
 * Created by Tohamy on 3/15/2018.
 */
class UsersFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    private val viewModel by viewModelProvider() {
        component.usersViewModel.also { it.loadUsers() }
    }

    lateinit var usersFragmentBinding: UsersFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        usersFragmentBinding = UsersFragmentBinding.inflate(inflater, container, false)
        return usersFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        usersFragmentBinding.refreshLayout.setOnRefreshListener(this@UsersFragment)
        usersFragmentBinding.refreshLayout.setColorSchemeResources(R.color.colorAccent)
        val adapter = DataBoundListAdapter { UsersViewHolder(it, viewModel) }
        usersFragmentBinding.usersRecycler.adapter = adapter
        usersFragmentBinding.usersRecycler.addItemDecoration(MarginDecoration(activity!!))
        viewModel.liveData.observe(this) {
            usersFragmentBinding.state = it
            adapter.replace(it.users())
            usersFragmentBinding.executePendingBindings()
        }
        viewModel.uiActions.observe(this) { it(activity!!) }
        usersFragmentBinding.viewModel = viewModel
    }

    override fun onRefresh() {
        usersFragmentBinding.noResultsText.visibility = View.GONE
        usersFragmentBinding.viewModel?.loadUsers()
    }

    companion object {
        fun newInstance(): UsersFragment = UsersFragment()
    }
}
