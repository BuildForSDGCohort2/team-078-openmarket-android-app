package com.buildforsdg.openmarket.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buildforsdg.openmarket.R
import com.buildforsdg.openmarket.extension.safelyNavigateTo
import com.buildforsdg.openmarket.ui.auth.model.LoginRequest
import com.buildforsdg.openmarket.ui.base.BaseFragment
import com.buildforsdg.openmarket.ui.utils.EventObserver
import com.buildforsdg.openmarket.ui.utils.InjectorUtils
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment() {

    private val viewModel by viewModels<LoginViewModel> {
        InjectorUtils.provideLoginViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSignUp.setOnClickListener { displayRegistrationScreen() }
        mbSignIn.setOnClickListener { loginViaEmail() }
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

            loginStatus.observe(viewLifecycleOwner,EventObserver {
                showSuccessDialog(it)
                // Navigate to home screen
            })
        }
    }

    private fun displayRegistrationScreen() {
        findNavController().safelyNavigateTo(R.id.action_loginFragment_to_registerFragment)
    }

    private fun loginViaEmail(){
        val request = LoginRequest(tieEmail.text.toString(),tiePassword.text.toString())
        viewModel.signInViaEmail(request)
    }
}