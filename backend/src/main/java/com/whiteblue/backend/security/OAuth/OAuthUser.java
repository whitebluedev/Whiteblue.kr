package com.whiteblue.backend.security.OAuth;

import com.whiteblue.backend.domain.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class OAuthUser implements UserDetails, OAuth2User {
    private final String username;

    private final String name;

    private final Collection<? extends GrantedAuthority> authorities;

    private final Map<String, Object> attributes;

    public OAuthUser(User user, Map<String, Object> attributes) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()
                                                                                    .getName()));
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
