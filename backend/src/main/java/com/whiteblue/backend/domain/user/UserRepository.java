package com.whiteblue.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    @Query("select distinct u from User u left join fetch u.board")
    List<User> findAll();

    Optional<User> findByUsername(String username);
}
