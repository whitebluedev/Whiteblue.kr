package com.whiteblue.backend.domain.application.entity;

import com.whiteblue.backend.domain.user.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phoneNumber;

    private String major;

    private String introduction;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @NotBlank
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User writer;

    @Builder
    public Application(Integer id, String name, String phoneNumber, String major, String introduction, User writer) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.major = major;
        this.introduction = introduction;
        this.writer = writer;
    }
}
