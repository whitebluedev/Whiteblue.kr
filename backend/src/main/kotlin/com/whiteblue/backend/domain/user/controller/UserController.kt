package com.whiteblue.backend.domain.user.controller

import com.whiteblue.backend.domain.user.dto.CreateUserRequest
import com.whiteblue.backend.domain.user.dto.UpdateUserRequest
import com.whiteblue.backend.domain.user.dto.UserResponse
import com.whiteblue.backend.domain.user.service.UserServiceImpl
import jakarta.validation.constraints.Positive
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/user")
@RestController
class UserController(private val userService: UserServiceImpl) {
    @GetMapping("/{id}")
    fun getUser(@PathVariable @Positive id: Long): ResponseEntity<UserResponse?> =
        ResponseEntity.ok(
            userService.getUserById(id)
        )

    @PostMapping("")
    fun createUser(createUserRequest: CreateUserRequest): ResponseEntity<UserResponse> =
        ResponseEntity.ok(
            userService.createUser(createUserRequest)
        )

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable @Positive id: Long,
        updateUserRequest: UpdateUserRequest
    ): ResponseEntity<UserResponse> =
        ResponseEntity.ok(
            userService.updateUser(id, updateUserRequest)
        )

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable @Positive id: Long): ResponseEntity<Unit> =
        ResponseEntity.ok(
            userService.deleteUser(id)
        )
}
