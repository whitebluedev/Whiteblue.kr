package com.whiteblue.backend.domain.board.repository

import com.whiteblue.backend.domain.board.model.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, Long>
