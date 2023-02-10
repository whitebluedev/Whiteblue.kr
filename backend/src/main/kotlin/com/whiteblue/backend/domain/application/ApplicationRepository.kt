package com.whiteblue.backend.domain.application

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ApplicationRepository : JpaRepository<Application, Long> {
    @Query("select distinct a from Application a left join fetch a.writer where a.writer.id = :id")
    fun findByWriterId(@Param("id") id: Long): Application?
}
