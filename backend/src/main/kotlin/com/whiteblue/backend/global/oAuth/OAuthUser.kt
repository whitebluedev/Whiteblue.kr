package com.whiteblue.backend.global.oAuth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

data class OAuthUser(
    val id: Long,

    private val username: String,

    private val name: String,

    private val authorities: List<GrantedAuthority>,

    private val attributes: Map<String, Any>?
) : UserDetails, OAuth2User {
    override fun getUsername(): String = username

    override fun getName(): String = name

    override fun getAuthorities(): List<GrantedAuthority> = authorities

    override fun getAttributes(): Map<String, Any>? = attributes

    override fun getPassword(): String? = null

    override fun isEnabled(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true
}
