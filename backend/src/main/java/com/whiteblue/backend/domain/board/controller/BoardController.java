package com.whiteblue.backend.domain.board.controller;

import com.whiteblue.backend.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {
    private final BoardService boardService;
}
