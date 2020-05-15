package com.cheise_proj.e_commerce.utils

import com.cheise_proj.e_commerce.model.User

object FakeAuthService {
    fun getUser(): User {
        return User(
            uid = "any_uid",
            photoUrl = "any_photo_url",
            email = "any_email",
            displayName = "any_display_name"
        )
    }
}