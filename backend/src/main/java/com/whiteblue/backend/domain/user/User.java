package com.whiteblue.backend.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.whiteblue.backend.domain.board.Board;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"board"})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(unique = true)
    private String username;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String image;

    private String refreshToken;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private final List<Board> board = new ArrayList<>();

    @Builder
    public User(String username, String name, UserRole role, String image) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.image = image;
    }
}
