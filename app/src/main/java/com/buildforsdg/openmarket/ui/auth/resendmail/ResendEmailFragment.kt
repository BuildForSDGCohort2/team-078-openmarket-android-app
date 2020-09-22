package com.buildforsdg.openmarket.ui.auth.resendmail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buildforsdg.openmarket.R
import com.buildforsdg.openmarket.ui.base.BaseFragment
import com.buildforsdg.openmarket.ui.utils.EventObserver

class ResendEmailFragment : BaseFragment() {

    private lateinit var viewModel: ResendEmailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.resend_email_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel){
            progress.observe(viewLifecycleOwner, EventObserver {
                toggleBlockingProgress(it)
            })

            error.observe(viewLifecycleOwner, EventObserver {
                showErrorDialog(it)
            })

            linkStatus.observe(viewLifecycleOwner, EventObserver{
                showAlertDialog(it,"Success", shouldPopStack = true)
            })
        }
    }

}