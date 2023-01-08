package com.whiteblue.backend.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;


@Getter
@JsonIgnoreProperties({"attributes", "authorities", "name"})
public class OAuthUser implements OAuth2User {
    private final Map<String, Object> attributes;

    private final String email;

    private final String username;

    public OAuthUser(Map<String, Object> attributes) {
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        this.attributes = attributes;
        this.email = (String) kakao_account.get("email");
        this.username = (String) properties.get("nickname");
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return "Kakao";
    }
}
