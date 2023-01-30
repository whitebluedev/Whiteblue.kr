package com.whiteblue.backend;

import com.whiteblue.backend.domain.application.repository.ApplicationRepository;
import com.whiteblue.backend.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@SpringBootTest
class BackendApplicationTests {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;


    @BeforeEach
    public void beforeTest() {
    }

    @Test
    public void JPATest() {
    }
}
