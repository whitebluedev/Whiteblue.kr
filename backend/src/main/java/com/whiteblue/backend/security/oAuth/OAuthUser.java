package com.whiteblue.backend.security.oAuth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthUser implements UserDetails, OAuth2User {
    @NotNull
    private Long id;

    @NotBlank
    @Email
    private String username;

    @NotBlank
    private String name;

    @NotNull
    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    @Builder
    public OAuthUser(Long id,
                     String username,
                     String name,
                     Collection<? extends GrantedAuthority> authorities,
                     Map<String, Object> attributes) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    @Deprecated
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
