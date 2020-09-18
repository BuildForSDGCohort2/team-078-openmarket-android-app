package com.buildforsdg.openmarket.ui.splash

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.buildforsdg.openmarket.R

/**
 * @author by Lawrence on 9/14/20.
 * for OpenMarket
 */

class SplashFragment : Fragment(){
    override fun onStart() {
        super.onStart()
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }
}