package com.whiteblue.backend.security.oAuth

import com.whiteblue.backend.domain.user.User
import com.whiteblue.backend.domain.user.UserRepository
import com.whiteblue.backend.domain.user.UserRole
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service
class OAuthUserService(private val userRepository: UserRepository):
    DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val attributes: Map<String, Any> = super.loadUser(userRequest).attributes
        val account = attributes["kakao_account"] as Map<*, *>
        val profile = account["profile"] as Map<*, *>
        val username = account["email"] as String
        val name = profile["nickname"] as String
        val image = if (profile["is_default_image"] as Boolean) null else profile["profile_image_url"] as String

        val user = userRepository.findByUsername(username)
            ?.let {
                it.name = name
                it.image = image
                userRepository.save(it)
            } ?: run { userRepository.save(User(username = username, name = name, image = image, role = UserRole.USER)) }

        return OAuthUser(
            id = user.id!!,
            username = user.username,
            name = user.name,
            authorities = Collections.singletonList(
                SimpleGrantedAuthority(
                    user.role.roleName
                )
            ),
            attributes = attributes
        )
    }
}
