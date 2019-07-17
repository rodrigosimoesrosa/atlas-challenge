package br.com.atlas.ui.view.adapter

import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.widget.RecyclerView
import android.view.ViewTreeObserver
import android.view.animation.Interpolator

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
class OnPreDrawItemListener(private val recyclerView: RecyclerView,
                            private val invisibleItem: Float = Values.ITEM_INVISIBLE,
                            private val visibleItem: Float = Values.ITEM_VISIBLE,
                            private val duration: Long = Values.ANIMATION_DURATION,
                            private val interpolator: Interpolator = FastOutSlowInInterpolator(),
                            private val delay: Long = Values.ITEM_DELAY) : ViewTreeObserver.OnPreDrawListener {

    object Values {
        const val ANIMATION_DURATION = 500L
        const val ITEM_DELAY = 200L

        const val ITEM_INVISIBLE = 0.0f
        const val ITEM_VISIBLE = 1.0f
    }

    override fun onPreDraw(): Boolean {
        recyclerView.viewTreeObserver.removeOnPreDrawListener(this)

        for (index in 0 until recyclerView.childCount) {
            val view = recyclerView.getChildAt(index)
            view.alpha = invisibleItem
            view.animate().alpha(visibleItem)
                .setDuration(duration)
                .setInterpolator(interpolator)
                .setStartDelay(index * delay)
                .start()
        }

        return true
    }

}
