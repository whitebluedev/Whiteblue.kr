package com.whiteblue.backend.domain.board

import com.whiteblue.backend.domain.user.User
import com.whiteblue.backend.util.BaseTimeEntity
import jakarta.persistence.*

@Table(name = "board")
@Entity
class Board(
    @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    val writer: User
): BaseTimeEntity() {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long? = null
}
