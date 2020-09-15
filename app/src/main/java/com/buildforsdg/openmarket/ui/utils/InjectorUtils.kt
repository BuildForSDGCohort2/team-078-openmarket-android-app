package com.buildforsdg.openmarket.ui.utils

import androidx.lifecycle.ViewModelProvider
import com.buildforsdg.openmarket.api.LoginService
import com.buildforsdg.openmarket.api.OpenMarketService
import com.buildforsdg.openmarket.api.RegisterService
import com.buildforsdg.openmarket.ui.auth.login.LoginRepository
import com.buildforsdg.openmarket.ui.auth.login.LoginViewModel
import com.buildforsdg.openmarket.ui.auth.register.RegisterRepository
import com.buildforsdg.openmarket.ui.auth.register.RegisterViewModel
import com.buildforsdg.openmarket.viewmodel.createFactory

/**
 * @author by Lawrence on 9/15/20.
 * for OpenMarket
 */

object InjectorUtils {
    private fun <T : Any>provideOpenMarketService(cls : Class<out T>): T{
        return OpenMarketService().create(cls)
    }

    private fun provideRegistrationRepository(): RegisterRepository {
        return RegisterRepository(provideOpenMarketService(RegisterService::class.java))
    }

    fun provideRegisterViewModelFactory(): ViewModelProvider.Factory{
        return RegisterViewModel(provideRegistrationRepository()).createFactory()
    }

    private fun provideLoginRepository() : LoginRepository {
        return LoginRepository(provideOpenMarketService(LoginService::class.java))
    }

    fun provideLoginViewModelFactory(): ViewModelProvider.Factory {
        return LoginViewModel(provideLoginRepository()).createFactory()
    }

}