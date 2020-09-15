package com.buildforsdg.openmarket.ui.auth.register

import com.buildforsdg.openmarket.api.RegisterService
import com.buildforsdg.openmarket.ui.auth.model.OpenMarketApiResponse
import com.buildforsdg.openmarket.ui.auth.model.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * @author by Lawrence on 9/14/20.
 * for OpenMarket
 */
class RegisterRepository(private val service : RegisterService){
    suspend fun registerUser(request: RegisterRequest) : OpenMarketApiResponse<String>{
        return service.registerUser(request)
    }

//    fun registerUser(request: RegisterRequest): Flow<OpenMarketApiResponse<String>>{
//        return flow {
//            emit(service.registerUser(request))
//        }.flowOn(Dispatchers.IO)
//    }
}
