package com.whiteblue.backend.domain.user.service

import com.whiteblue.backend.domain.user.dto.CreateUserRequest
import com.whiteblue.backend.domain.user.dto.UpdateUserRequest
import com.whiteblue.backend.domain.user.dto.UserResponse

interface UserService {
    fun getUserById(id: Long): UserResponse?

    fun createUser(createUserRequest: CreateUserRequest): UserResponse

    fun updateUser(id: Long, updateUserRequest: UpdateUserRequest): UserResponse

    fun deleteUser(id: Long)
}
