package com.buildforsdg.openmarket.api

import com.buildforsdg.openmarket.ui.auth.model.GoogleAuthRequest
import com.buildforsdg.openmarket.ui.auth.model.LoginData
import com.buildforsdg.openmarket.ui.auth.model.LoginRequest
import com.buildforsdg.openmarket.ui.auth.model.OpenMarketApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author by Lawrence on 9/15/20.
 * for OpenMarket
 */
interface LoginService {
    @POST("api/v1/auth/login")
    suspend fun userLogin(@Body request : LoginRequest) : OpenMarketApiResponse<LoginData>

    @POST("api/v1/auth/google")
    suspend fun googleAuth(@Body request : GoogleAuthRequest) : OpenMarketApiResponse<String>

    @GET("/api/v1/auth/verification/resend")
    suspend fun resendVerificationTo(
        @Query("email") email : String
    ) : OpenMarketApiResponse<String>
}