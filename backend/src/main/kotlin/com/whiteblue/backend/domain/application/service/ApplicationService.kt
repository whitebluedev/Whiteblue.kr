package com.whiteblue.backend.domain.application.service

import com.whiteblue.backend.domain.application.dto.ApplicationResponse
import com.whiteblue.backend.domain.application.dto.CreateApplicationRequest
import com.whiteblue.backend.domain.application.dto.UpdateApplicationRequest

interface ApplicationService {
    fun getApplicationByWriterId(writerId: Long): ApplicationResponse?

    fun createApplication(writerId: Long, createApplicationRequest: CreateApplicationRequest): ApplicationResponse

    fun updateApplication(id: Long, updateApplicationRequest: UpdateApplicationRequest): ApplicationResponse

    fun deleteApplication(id: Long)
}
