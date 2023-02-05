package com.whiteblue.backend.domain.application.DTO;

import com.whiteblue.backend.domain.application.Application;
import com.whiteblue.backend.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveApplicationDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String major;

    @NotBlank
    private String introduction;

    private User writer;

    @Builder
    public SaveApplicationDTO(String name, String phoneNumber, String major, String introduction, User writer) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.major = major;
        this.introduction = introduction;
        this.writer = writer;
    }

    public Application toEntity() {
        return Application.builder()
                          .name(name)
                          .phoneNumber(phoneNumber)
                          .major(major)
                          .introduction(introduction)
                          .writer(writer)
                          .build();
    }
}

