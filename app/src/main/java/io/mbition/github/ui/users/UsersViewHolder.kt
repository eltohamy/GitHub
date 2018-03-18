package io.mbition.github.ui.users

import android.view.ViewGroup
import io.mbition.github.databinding.RowUserBinding
import io.mbition.github.models.User
import io.mbition.github.utils.DataBoundViewHolder

/**
 * Created by Tohamy on 3/15/2018.
 */
class UsersViewHolder(parent: ViewGroup, private val viewModel: UsersViewModel) :
        DataBoundViewHolder<User, RowUserBinding>(parent, RowUserBinding::inflate) {
    init {
        binding.root.setOnClickListener {
            viewModel.openUserDetails(item.id)
        }
    }

    override fun bind(t: User) {
        binding.user = t
    }
}