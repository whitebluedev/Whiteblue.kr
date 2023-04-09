package com.whiteblue.backend.domain.board.service

import com.whiteblue.backend.domain.board.repository.BoardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class BoardServiceImpl(private val boardRepository: BoardRepository) : BoardService
