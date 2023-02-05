package com.whiteblue.backend.domain.application;

import com.whiteblue.backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    @Query("select distinct a from Application a left join fetch a.writer where a.writer = :user")
    Optional<Application> findByWriter(@Param("user") User user);
}