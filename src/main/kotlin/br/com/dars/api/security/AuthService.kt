package br.com.dars.api.security

import br.com.dars.api.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthService (
    private val userService: UserService
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        return userService.findUserByLogin(username)
    }
}