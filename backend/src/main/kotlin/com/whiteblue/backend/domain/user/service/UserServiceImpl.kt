package com.whiteblue.backend.domain.user.service

import com.whiteblue.backend.domain.user.UserRole
import com.whiteblue.backend.domain.user.dto.CreateUserRequest
import com.whiteblue.backend.domain.user.dto.UpdateUserRequest
import com.whiteblue.backend.domain.user.dto.UserResponse
import com.whiteblue.backend.domain.user.model.User
import com.whiteblue.backend.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    @Transactional(readOnly = true)
    override fun getUserById(id: Long): UserResponse? =
        userRepository.findByIdOrNull(id)
            ?.let {
                UserResponse.from(it)
            }

    override fun createUser(createUserRequest: CreateUserRequest): UserResponse =
        User(
            username = createUserRequest.username,
            name = createUserRequest.name,
            image = createUserRequest.image,
            role = UserRole.USER
        ).let {
            userRepository.save(it)
            UserResponse.from(it)
        }

    override fun updateUser(id: Long, updateUserRequest: UpdateUserRequest): UserResponse =
        userRepository.findByIdOrNull(id)!!
            .apply {
                name = updateUserRequest.name
                image = updateUserRequest.name
            }
            .let {
                UserResponse.from(it)
            }

    override fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}
