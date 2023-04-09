package com.whiteblue.backend.domain.application.dto

import com.whiteblue.backend.domain.application.model.Application
import com.whiteblue.backend.domain.user.model.User

data class ApplicationResponse(
    val id: Long,

    val name: String,

    val phoneNumber: String,

    val major: String,

    val introduction: String,

    val writer: User,
) {
    companion object {
        fun from(application: Application): ApplicationResponse =
            ApplicationResponse(
                id = application.id!!,
                name = application.name,
                phoneNumber = application.phoneNumber,
                major = application.major,
                introduction = application.major,
                writer = application.writer
            )
    }
}
