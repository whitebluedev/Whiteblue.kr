package com.whiteblue.backend.domain.application.repository

import com.whiteblue.backend.domain.application.model.Application
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ApplicationRepository : JpaRepository<Application, Long> {
    @Query("select distinct a from Application a left join fetch a.writer where a.writer.id = :id")
    fun findByWriterId(@Param("id") id: Long): Application?
}
