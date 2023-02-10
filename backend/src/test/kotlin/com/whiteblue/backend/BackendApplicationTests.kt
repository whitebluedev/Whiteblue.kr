package com.whiteblue.backend

import com.whiteblue.backend.domain.user.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class BackendApplicationTests @Autowired constructor(
    private val userRepository: UserRepository
) {

    @Rollback(value = false)
    @Transactional
    @Test
    fun contextLoads() {
        userRepository.findByIdOrNull(1)
            ?.let {
                it.name = "zzzz"
            }
    }
}
