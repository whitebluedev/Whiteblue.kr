package com.whiteblue.backend.domain.user

import com.whiteblue.backend.domain.board.Board
import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Table(name = "user")
@Entity
class User(
    @field:Email
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
