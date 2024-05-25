package br.com.dars.api.service

import br.com.dars.api.entities.User
import br.com.dars.api.exceptions.NotFoundException
import br.com.dars.api.mapper.UserDetailMapper
import br.com.dars.api.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService (
    private val userDetailMapper: UserDetailMapper,
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    fun findUserByLogin(username: String): UserDetails? {
        var user = userRepository.findByUsername(username)
        if (user.isPresent) {
            return userDetailMapper.mapByUsername(user.get())
        }
        user = userRepository.findByEmail(username)
        if (user.isPresent) {
            return userDetailMapper.mapByEmail(user.get())
        }
        throw NotFoundException("User not found.")
    }

    fun findByUsername(username: String): Optional<User> {
        return userRepository.findByUsername(username)
    }

    fun save(newUser: User): User {
        newUser.password = passwordEncoder.encode(newUser.password)
        return userRepository.save(newUser)
    }
}