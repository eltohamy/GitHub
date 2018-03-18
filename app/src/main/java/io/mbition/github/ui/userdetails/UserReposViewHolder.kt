package io.mbition.githubui.user


import android.view.ViewGroup
import io.mbition.github.databinding.RowRepoBinding
import io.mbition.github.models.Repo
import io.mbition.github.utils.DataBoundViewHolder

/**
 * Created by Tohamy on 3/15/2018.
 */

class UserReposViewHolder(parent: ViewGroup) :
        DataBoundViewHolder<Repo, RowRepoBinding>(parent, RowRepoBinding::inflate) {
    init {

    }

    override fun bind(t: Repo) {
        binding.repo = t
    }
}

