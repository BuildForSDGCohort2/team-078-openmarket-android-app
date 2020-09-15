package com.buildforsdg.openmarket.ui.auth.model

data class OpenMarketApiResponse<out T>(
    val message: String?,
    val status: String?,
    val data: T?
)