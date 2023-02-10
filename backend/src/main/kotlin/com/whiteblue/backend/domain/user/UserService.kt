package com.whiteblue.backend.domain.user

import com.whiteblue.backend.domain.user.dto.GetUserResponse
import com.whiteblue.backend.security.oAuth.OAuthUser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserService(private val userRepository: UserRepository) {
    @Transactional(readOnly = true)
    fun findByAuth(oAuthUser: OAuthUser): GetUserResponse {
        return userRepository.findByOAuthUser(oAuthUser)
            .run {
                GetUserResponse(
                    username = username,
                    name = name,
                    image = image
                )
            }
    }
}
