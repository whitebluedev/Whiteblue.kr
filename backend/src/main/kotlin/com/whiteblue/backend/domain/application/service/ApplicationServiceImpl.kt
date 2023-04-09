package com.whiteblue.backend.domain.application.service

import com.whiteblue.backend.domain.application.dto.ApplicationResponse
import com.whiteblue.backend.domain.application.dto.CreateApplicationRequest
import com.whiteblue.backend.domain.application.dto.UpdateApplicationRequest
import com.whiteblue.backend.domain.application.model.Application
import com.whiteblue.backend.domain.application.repository.ApplicationRepository
import com.whiteblue.backend.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ApplicationServiceImpl(
    private val applicationRepository: ApplicationRepository,

    private val userRepository: UserRepository
) : ApplicationService {
    @Transactional(readOnly = true)
    override fun getApplicationByWriterId(writerId: Long): ApplicationResponse? =
        applicationRepository.findByWriterId(writerId)
            ?.let {
                ApplicationResponse.from(it)
            }

    override fun createApplication(
        writerId: Long,
        createApplicationRequest: CreateApplicationRequest
    ): ApplicationResponse =
        Application(
            name = createApplicationRequest.name,
            phoneNumber = createApplicationRequest.phoneNumber,
            major = createApplicationRequest.major,
            introduction = createApplicationRequest.introduction,
            writer = userRepository.findByIdOrNull(writerId)!!
        ).let {
            applicationRepository.save(it)
            ApplicationResponse.from(it)
        }

    override fun updateApplication(id: Long, updateApplicationRequest: UpdateApplicationRequest): ApplicationResponse =
        applicationRepository.findByIdOrNull(id)!!
            .apply {
                name = updateApplicationRequest.name
                phoneNumber = updateApplicationRequest.phoneNumber
                major = updateApplicationRequest.major
                introduction = updateApplicationRequest.introduction
            }
            .let {
                ApplicationResponse.from(it)
            }

    override fun deleteApplication(id: Long) {
        applicationRepository.deleteById(id)
    }
}
