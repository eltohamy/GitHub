package io.mbition.github.utils


import android.content.Context
import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.view.KeyEvent
import android.view.View
import android.view.View.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.mbition.github.R
import io.mbition.github.models.GenericResource
import io.mbition.github.models.Resource

/**
 * Created by Tohamy on 3/14/18.
 */

@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else INVISIBLE
    }

@set:BindingAdapter("invisible")
var View.invisible
    get() = visibility == INVISIBLE
    set(value) {
        visibility = if (value) INVISIBLE else VISIBLE
    }

@set:BindingAdapter("gone")
var View.gone
    get() = visibility == GONE
    set(value) {
        visibility = if (value) GONE else VISIBLE
    }

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("errorMessage")
fun bindErrorMessage(t: TextView, resource: GenericResource?) {
    t.text = when {
        resource !is Resource.Error -> ""
        resource.message.isBlank() -> t.resources.getString(R.string.error_try_again)
        else -> resource.message
    }
}

@BindingAdapter("visibleWhileLoading")
fun bindVisibleWhileLoading(t: View, resource: GenericResource?) {
    t.visibility = if (resource is Resource.Loading) VISIBLE else GONE
}

@BindingAdapter("visibleOnError")
fun bindVisibleOnError(t: View, resource: GenericResource?) {
    t.visibility = if (resource is Resource.Error) VISIBLE else GONE
}

@BindingAdapter("refreshingWhileLoading")
fun bindRefreshingWhileLoading(t: View, resource: GenericResource?) {
    t as SwipeRefreshLayout
    t.isRefreshing = resource is Resource.Loading
}