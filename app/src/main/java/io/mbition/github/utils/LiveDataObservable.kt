package io.mbition.github.utils

import android.arch.lifecycle.LifecycleOwner

/**
 * Created by Tohamy on 3/15/2018.
 */
interface LiveDataObservable<out T> {

    fun observe(owner: LifecycleOwner, observer: (T) -> Unit)

    fun observeForever(observer: (T) -> Unit)
}