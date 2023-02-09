package com.whiteblue.backend.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    @Query("select distinct u from User u left join fetch u.board")
    override fun findAll(): List<User>

    fun findByUsername(username: String): User?

    @Query("select u.refreshToken from User u where u.id = :id")
    fun getRefreshTokenById(@Param("id") id: Long): String?

    @Modifying
    @Query("update User u set u.refreshToken = :refreshToken where u.id = :id")
    fun updateRefreshToken(@Param("id") id: Long, @Param("refreshToken") refreshToken: String)
}
