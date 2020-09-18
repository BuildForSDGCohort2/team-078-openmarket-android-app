package com.buildforsdg.openmarket.ui.auth.model

data class LoginData(
    val canLogin: Boolean,
    val isLockedOut: Boolean,
    val isNotAllowed: Boolean,
    val requiresTwoFactor: Boolean,
    val user: User
)