package com.buildforsdg.openmarket.ui.auth.model

data class User(
    val address: String,
    val alpha2CountryCode: String,
    val city: String,
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phoneNumber: String,
    val scope: String,
    val state: String
)