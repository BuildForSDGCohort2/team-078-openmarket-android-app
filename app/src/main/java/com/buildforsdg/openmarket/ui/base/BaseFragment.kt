package com.buildforsdg.openmarket.ui.base

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.buildforsdg.openmarket.R
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

    fun showSuccessDialog(message: String, title: String = getString(R.string.title_api_success)){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        builder.create().show()
    }

    fun showAlertDialog(message: String, title: String){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        builder.create().show()
    }

    fun showErrorDialog(message: String, title: String = getString(R.string.title_api_error)){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        builder.create().show()
    }
}