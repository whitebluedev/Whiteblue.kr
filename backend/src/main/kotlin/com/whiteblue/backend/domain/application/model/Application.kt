package com.whiteblue.backend.domain.application.model

import com.whiteblue.backend.domain.user.model.User
import com.whiteblue.backend.global.common.BaseEntity
import jakarta.persistence.*

@Table(name = "application")
@Entity
class Application(
    var name: String,

    var phoneNumber: String,

    var major: String,

    var introduction: String,

    @OneToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    val writer: User
) : BaseEntity() {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long? = null
}
