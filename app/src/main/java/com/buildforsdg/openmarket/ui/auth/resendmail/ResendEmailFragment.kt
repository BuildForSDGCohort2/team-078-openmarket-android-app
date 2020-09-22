package com.buildforsdg.openmarket.ui.auth.resendmail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.buildforsdg.openmarket.R
import com.buildforsdg.openmarket.ui.base.BaseFragment
import com.buildforsdg.openmarket.ui.utils.EventObserver
import com.buildforsdg.openmarket.ui.utils.InjectorUtils
import kotlinx.android.synthetic.main.resend_email_fragment.*

class ResendEmailFragment : BaseFragment() {

    private val viewModel by viewModels<ResendEmailViewModel> {
        InjectorUtils.provideResendEmailViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.resend_email_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mbRequestVerificationLink.setOnClickListener {
            requestEmailVerificationLink()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            progress.observe(viewLifecycleOwner, EventObserver {
                toggleBlockingProgress(it)
            })

            error.observe(viewLifecycleOwner, EventObserver {
                showErrorDialog(it)
            })

            linkStatus.observe(viewLifecycleOwner, EventObserver {
                showAlertDialog(it, "Success", shouldPopStack = true)
            })
        }
    }

    private fun requestEmailVerificationLink(){
        if(tieEmail.text.toString().isNotEmpty()){
            viewModel.resendLinkTo(tieEmail.text.toString())
        }else{
            tilEmail.isErrorEnabled = true
            tilEmail.error = "Email Address is Required"
        }
    }

}