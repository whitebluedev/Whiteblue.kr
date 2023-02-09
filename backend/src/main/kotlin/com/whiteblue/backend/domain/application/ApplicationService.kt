package com.whiteblue.backend.domain.application

import com.whiteblue.backend.domain.application.dto.GetApplicationResponse
import com.whiteblue.backend.domain.application.dto.SaveApplicationRequest
import com.whiteblue.backend.domain.user.UserRepository
import com.whiteblue.backend.security.oAuth.OAuthUser
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ApplicationService(
    private val applicationRepository: ApplicationRepository,

    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun findByUser(oAuthUser: OAuthUser): GetApplicationResponse? {
        val user = userRepository.findByIdOrNull(oAuthUser.id)!!
        return applicationRepository.findByWriterId(user.id!!)
            ?.run {
                GetApplicationResponse(
                    name = name,
                    phoneNumber = phoneNumber,
                    major = major,
                    introduction = introduction,
                    writer = user
                )
            }
    }

    fun save(saveApplicationRequest: SaveApplicationRequest, oAuthUser: OAuthUser) {
        applicationRepository.findByWriterId(oAuthUser.id)!!
            .apply {
                name = saveApplicationRequest.name
                phoneNumber = saveApplicationRequest.phoneNumber
                major = saveApplicationRequest.major
                introduction = saveApplicationRequest.introduction
            }
    }
}