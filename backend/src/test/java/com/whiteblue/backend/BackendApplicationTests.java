package com.whiteblue.backend;

import com.whiteblue.backend.security.jwt.JWTProvider;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
class BackendApplicationTests {
    @Autowired
    private JWTProvider jwtProvider;

    @BeforeEach
    public void beforeTest() {
    }

    @Test
    public void springTest() {
        Claims claims = jwtProvider.parseClaims(
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3NfdG9rZW4iLCJpc3MiOiJXaGl0ZWJsdWUiLCJpYXQiOjE2NzU2OTIxOTEsImlkIjoyLCJ1c2VybmFtZSI6InNhbjA2MDM2QGtha2FvLmNvbSIsIm5hbWUiOiLsoJXsg4HsnKQiLCJyb2xlIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjc1Njk1NzkxfQ.7cMXxhNcNUE6964tfoKtmf3gYi2C8-6VBnzZsVgAk6T1lJp0e8B03pYDfWjKlbofLTBnjSPGTPo84krQNVK42A");

        System.out.println(claims.get("username"));
    }
}
