package com.whiteblue.backend.domain.application.repository;

import com.whiteblue.backend.domain.application.entity.Application;
import com.whiteblue.backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    @Query("select distinct a from Application a left join fetch a.writer")
    Optional<Application> findByWriter(User user);
}
