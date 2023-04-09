package com.whiteblue.backend.domain.board.model

import com.whiteblue.backend.domain.user.model.User
import com.whiteblue.backend.global.common.BaseEntity
import jakarta.persistence.*

@Table(name = "board")
@Entity
class Board(
    var title: String,

    var content: String,

    @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    val writer: User
) : BaseEntity() {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long? = null
}
