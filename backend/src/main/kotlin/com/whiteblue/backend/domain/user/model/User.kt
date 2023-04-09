package com.whiteblue.backend.domain.user.model

import com.whiteblue.backend.domain.board.model.Board
import com.whiteblue.backend.domain.user.UserRole
import jakarta.persistence.*

@Table(name = "user")
@Entity
class User(
    @field:Column(unique = true)
    var username: String,

    var name: String,

    var image: String?,

    @Enumerated(EnumType.STRING)
    var role: UserRole,
) {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long? = null

    var refreshToken: String? = null

    @OneToMany(mappedBy = "writer", cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY)
    val board: List<Board> = ArrayList()
}
