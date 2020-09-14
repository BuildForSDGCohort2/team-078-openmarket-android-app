package com.buildforsdg.openmarket.ui.auth.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.buildforsdg.openmarket.R
import com.buildforsdg.openmarket.extension.safelyNavigateTo
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSignIn.setOnClickListener { displayLoginScreen() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun displayLoginScreen(){
//        findNavController().safelyNavigateTo(R.id.action_registerFragment_to_loginFragment)
        findNavController().popBackStack(R.id.loginFragment,false)
    }

}