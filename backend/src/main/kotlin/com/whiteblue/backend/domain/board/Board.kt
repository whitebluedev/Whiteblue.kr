package com.whiteblue.backend.domain.board

import com.whiteblue.backend.domain.user.User
import com.whiteblue.backend.util.BaseTimeEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Table(name = "board")
@Entity
class Board(
    @field:NotBlank
    var title: String,

    @field:NotBlank
    var content: String,

    @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    val writer: User
) : BaseTimeEntity() {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long? = null
}
