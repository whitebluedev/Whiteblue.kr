package com.whiteblue.backend.domain.user.dto

import com.whiteblue.backend.domain.user.model.User

data class UserResponse(
    val username: String,

    val name: String,

    val image: String?
) {
    companion object {
        fun from(user: User): UserResponse =
            UserResponse(
                username = user.username,
                name = user.name,
                image = user.image
            )
    }
}
