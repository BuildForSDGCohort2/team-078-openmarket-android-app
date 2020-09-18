package com.buildforsdg.openmarket.ui.auth.login

import com.buildforsdg.openmarket.api.LoginService
import com.buildforsdg.openmarket.ui.auth.model.*

/**
 * @author by Lawrence on 9/15/20.
 * for OpenMarket
 */
class LoginRepository(private val service : LoginService){
    suspend fun userLogin(request: LoginRequest) : OpenMarketApiResponse<LoginData> {
        return service.userLogin(request)
    }

    suspend fun googleAuth(request: GoogleAuthRequest) : OpenMarketApiResponse<String>{
        return service.googleAuth(request)
    }
}