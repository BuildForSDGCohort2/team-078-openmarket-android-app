package com.buildforsdg.openmarket.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.buildforsdg.openmarket.R
import com.buildforsdg.openmarket.constant.Constants
import com.buildforsdg.openmarket.extension.safelyNavigateTo
import com.buildforsdg.openmarket.ui.auth.model.GoogleAuthRequest
import com.buildforsdg.openmarket.ui.auth.model.LoginRequest
import com.buildforsdg.openmarket.ui.base.BaseFragment
import com.buildforsdg.openmarket.ui.utils.EventObserver
import com.buildforsdg.openmarket.ui.utils.InjectorUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : BaseFragment() {
    private lateinit var googleSignInClient: GoogleSignInClient

    private val viewModel by viewModels<LoginViewModel> {
        InjectorUtils.provideLoginViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        account?.let {
            // navigate to home screen
            showHomeScreen()
        }

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
        mbSignInWithGoogle.setOnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, Constants.RC_SIGN_IN)
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

            loginStatus.observe(viewLifecycleOwner,EventObserver {
                when {
                    it.canLogin -> {
                        // save user object and token
                        //it.user it.auth_token
                        showHomeScreen()
                    }
                    it.isLockedOut -> {
                        showAlertDialog(getString(R.string.contact_admin),"Account Locked")
                    }
                    else -> {
                        // email verification required
                    }
                }
            })

            googleAuthHandshake.observe(viewLifecycleOwner, EventObserver{
                // Navigate to home screen
                showHomeScreen()
            })
        }
    }

    private fun showHomeScreen(){
        findNavController().safelyNavigateTo(R.id.action_loginFragment_to_homeFragment)
    }

    private fun displayRegistrationScreen() {
        findNavController().safelyNavigateTo(R.id.action_loginFragment_to_registerFragment)
    }

    private fun loginViaEmail(){
        val request = LoginRequest(tieEmail.text.toString(),tiePassword.text.toString())
        viewModel.signInViaEmail(request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.idToken?.let {
                    viewModel.authenticateWithGoogle(GoogleAuthRequest(it))
                }
            } catch (e: ApiException) {
                e.message?.let {
                    showErrorDialog(it, "Google Sign In Failed")
                }
            }
        }
    }
}