package com.whiteblue.backend.domain.user.entity;

import com.whiteblue.backend.domain.application.entity.Application;
import com.whiteblue.backend.domain.board.entity.Board;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
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

    @Builder
    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
