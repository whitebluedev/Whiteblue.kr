package com.whiteblue.backend.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.whiteblue.backend.domain.application.entity.Application;
import com.whiteblue.backend.domain.board.entity.Board;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"attributes", "authorities", "name"})
@Entity
public class User implements OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String username;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private final List<Board> board = new ArrayList<>();

    @OneToOne(mappedBy = "writer", cascade = CascadeType.REMOVE)
    private Application application;

    @Transient
    private Map<String, Object> attributes;

    @Builder
    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public User(Map<String, Object> attributes) {
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        this.attributes = attributes;
        this.email = (String) kakao_account.get("email");
        this.username = (String) properties.get("nickname");
    }

    @Deprecated
    @Override
    public List<GrantedAuthority> getAuthorities() {
        return null;
    }

    @Deprecated
    @Override
    public String getName() {
        return "Kakao";
    }

}
