package com.whiteblue.backend.util

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {
    @NotNull
    @CreatedDate
    @Column(insertable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @NotNull
    @LastModifiedDate
    @Column(insertable = false)
    var modifiedAt: LocalDateTime = LocalDateTime.now()
}
