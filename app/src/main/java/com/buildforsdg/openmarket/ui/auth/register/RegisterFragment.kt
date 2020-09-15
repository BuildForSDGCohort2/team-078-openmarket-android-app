package com.buildforsdg.openmarket.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buildforsdg.openmarket.R
import com.buildforsdg.openmarket.ui.auth.model.RegisterRequest
import com.buildforsdg.openmarket.ui.base.BaseFragment
import com.buildforsdg.openmarket.ui.utils.EventObserver
import com.buildforsdg.openmarket.ui.utils.InjectorUtils
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : BaseFragment() {

    private val viewModel by viewModels<RegisterViewModel>{InjectorUtils.provideRegisterViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSignIn.setOnClickListener { displayLoginScreen() }
        mbSignUp.setOnClickListener { signUpUser() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel){
            progress.observe(viewLifecycleOwner, EventObserver{
                toggleBlockingProgress(it)
            })

            error.observe(viewLifecycleOwner, EventObserver {
                showErrorDialog(it)
            })

            registrationStatus.observe(viewLifecycleOwner, EventObserver{
                showSuccessDialog(it)
            })
        }
    }

    private fun displayLoginScreen(){
        findNavController().popBackStack(R.id.loginFragment,false)
    }

    private fun signUpUser(){
        val request = RegisterRequest(tieEmail.text.toString(),
            tieFirstName.text.toString(),tieLastName.text.toString(),tiePassword.text.toString()
        )
        viewModel.register(request)
    }

}