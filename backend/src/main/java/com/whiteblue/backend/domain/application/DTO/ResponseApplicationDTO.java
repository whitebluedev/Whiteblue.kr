package com.whiteblue.backend.domain.application.DTO;

import com.whiteblue.backend.domain.application.entity.Application;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseApplicationDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String major;

    @NotBlank
    private String introduction;

    @Builder
    public ResponseApplicationDTO(String name, String phoneNumber, String major, String introduction) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.major = major;
        this.introduction = introduction;
    }

    public Application toEntity() {
        return Application.builder()
                          .name(name)
                          .phoneNumber(phoneNumber)
                          .major(major)
                          .introduction(introduction)
                          .build();
    }
}

