package com.buildforsdg.openmarket.api

import com.buildforsdg.openmarket.ui.auth.model.OpenMarketApiResponse
import com.buildforsdg.openmarket.ui.auth.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author by Lawrence on 9/15/20.
 * for OpenMarket
 */
interface RegisterService {
    @POST("api/v1/auth/register")
    suspend fun registerUser(@Body request : RegisterRequest) : OpenMarketApiResponse<String>
}