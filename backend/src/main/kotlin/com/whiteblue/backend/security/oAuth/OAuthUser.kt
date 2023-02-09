package com.whiteblue.backend.security.oAuth

import jakarta.validation.constraints.Email
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

data class OAuthUser(
    val id: Long,

    @field:Email
    private val username: String,

    private val name: String,

    private val authorities: List<GrantedAuthority>,

    private val attributes: Map<String, Any>?
): UserDetails, OAuth2User {
    override fun getName(): String {
        return name
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAttributes(): Map<String, Any>? {
        return attributes
    }

    override fun getAuthorities(): List<GrantedAuthority> {
        return authorities
    }
}
