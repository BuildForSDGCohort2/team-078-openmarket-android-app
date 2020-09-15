package com.buildforsdg.openmarket.ui.auth.model

data class RegisterRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String
)