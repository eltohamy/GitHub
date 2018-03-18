package io.mbition.github.utils

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import io.mbition.github.R

/**
 * Created by Tohamy on 3/15/18.
 */


class MarginDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val margin: Int

    init {
        margin = context.resources.getDimensionPixelSize(R.dimen.item_margin)
    }

    override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.set(margin, margin, margin, margin)
    }
}
