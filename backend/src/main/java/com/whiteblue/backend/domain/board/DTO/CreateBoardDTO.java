package com.whiteblue.backend.domain.board.DTO;

import com.whiteblue.backend.domain.board.Board;
import com.whiteblue.backend.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateBoardDTO {
    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private User writer;

    @Builder
    public CreateBoardDTO(String title, String content, User writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board toEntity() {
        return Board.builder()
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .build();
    }
}

