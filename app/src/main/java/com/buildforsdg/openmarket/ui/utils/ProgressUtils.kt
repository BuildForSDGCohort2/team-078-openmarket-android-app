package com.buildforsdg.openmarket.ui.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.buildforsdg.openmarket.R


/**
 * @author by Lawrence on 9/14/20.
 * for OpenMarket
 */

class ProgressUtils {
    private var mDialog: ProgressBar? = null

    companion object {
        @Volatile
        private var INSTANCE: ProgressUtils? = null

        fun getInstance(): ProgressUtils {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = ProgressUtils()
                INSTANCE = instance
                return instance
            }
        }
    }

    fun showProgress(context: Context) {
        mDialog?.let {
            if (it.isShown) {
                return
            }
        }

        mDialog = ProgressBar(context, null, android.R.style.Widget_Material_ProgressBar_Large)
        mDialog?.let {
            it.indeterminateDrawable.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                ), android.graphics.PorterDuff.Mode.SRC_IN
            )

            it.visibility = View.VISIBLE
        }
    }

    fun hideProgress() {
        mDialog?.let {
            if (it.isShown) {
                it.visibility = View.GONE
                mDialog = null
            }
        }
    }
}