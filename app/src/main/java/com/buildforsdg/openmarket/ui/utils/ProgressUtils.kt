package com.buildforsdg.openmarket.ui.utils

import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.buildforsdg.openmarket.R
import com.buildforsdg.openmarket.extension.makeGone
import com.buildforsdg.openmarket.extension.makeVisible


/**
 * @author by Lawrence on 9/14/20.
 * for OpenMarket
 */

class ProgressUtils {
    private var mDialog: Dialog? = null

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
            if (it.isShowing) { return }
        }

        mDialog = Dialog(context)
        mDialog?.let {
            it.requestWindowFeature(Window.FEATURE_NO_TITLE)
            it.setContentView(R.layout.dialog_progress)

            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            it.show()
        }
    }

    fun hideProgress() {
        mDialog?.let {
            if (it.isShowing) {
                it.dismiss()
                mDialog = null
            }
        }
    }
}