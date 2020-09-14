package com.buildforsdg.openmarket.ui.base

import androidx.fragment.app.Fragment
import com.buildforsdg.openmarket.ui.utils.ProgressUtils

/**
 * @author by Lawrence on 9/14/20.
 * for OpenMarket
 */

abstract class BaseFragment : Fragment(){
    private fun showBlockingProgress() {
        ProgressUtils.getInstance()
            .showProgress(requireContext())
    }

    private fun hideBlockingProgress() {
        ProgressUtils.getInstance().hideProgress()
    }

    fun toggleBlockingProgress(value: Boolean) {
        if (value) {
            showBlockingProgress()
        } else {
            hideBlockingProgress()
        }
    }
}