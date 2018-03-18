package io.mbition.github.utils

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Tohamy on 3/15/2018.
 */
abstract class DataBoundViewHolder<T : Any, out V : ViewDataBinding>
private constructor(val binding: V) :
        RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup, factory: (LayoutInflater, ViewGroup, Boolean) -> V) :
            this(factory(LayoutInflater.from(parent.context), parent, false))

    lateinit var item: T

    abstract fun bind(t: T)
}