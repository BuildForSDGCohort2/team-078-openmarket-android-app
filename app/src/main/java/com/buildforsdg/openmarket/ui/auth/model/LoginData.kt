package com.buildforsdg.openmarket.ui.auth.model

data class LoginData(
    val auth_token: String?,
    val canLogin: Boolean,
    val isLockedOut: Boolean,
    val isNotAllowed: Boolean,
    val requiresTwoFactor: Boolean,
    val user: User,
    val roles: List<String>?
)