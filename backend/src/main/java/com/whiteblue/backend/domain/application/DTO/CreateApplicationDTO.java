package com.whiteblue.backend.domain.application.DTO;

import com.whiteblue.backend.domain.application.entity.Application;
import com.whiteblue.backend.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateApplicationDTO {
    @NotBlank
    private User writer;

    @Builder
    public CreateApplicationDTO(User writer) {
        this.writer = writer;
    }

    public Application toEntity() {
        return Application.builder()
                          .writer(writer)
                          .build();
    }
}

