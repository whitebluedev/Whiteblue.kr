package com.whiteblue.backend.domain.application

import com.whiteblue.backend.domain.user.User
import com.whiteblue.backend.util.BaseTimeEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Table(name = "application")
@Entity
class Application(
    @field:NotNull
    var name: String,

    @field:NotNull
    var phoneNumber: String,

    @field:NotNull
    var major: String,

    @field:NotNull
    var introduction: String,

    @field:NotNull
    @OneToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    var writer: User
) : BaseTimeEntity() {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Int? = null
}
