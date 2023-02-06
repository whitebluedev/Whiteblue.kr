package com.whiteblue.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @Query("select distinct u from User u left join fetch u.board")
    List<User> findAll();

    Optional<User> findByUsername(String username);

    @Query("select u.refreshToken from User u where u.id = :id")
    String getRefreshTokenById(@Param("id") Long id);

    @Modifying
    @Query("update User u set u.refreshToken = :refreshToken where u.id = :id")
    void updateRefreshToken(@Param("id") Long id, @Param("refreshToken") String refreshToken);
}
