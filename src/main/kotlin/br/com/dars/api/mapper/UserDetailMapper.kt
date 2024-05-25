package br.com.dars.api.mapper

import br.com.dars.api.entities.User
import br.com.dars.api.security.UserDetail
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class UserDetailMapper {
    fun mapByUsername(user: User): UserDetail {
        return mountUserDetail(user.username, user)
    }

    fun mapByEmail(user: User): UserDetail {
        return mountUserDetail(user.email, user)
    }

    private fun mountUserDetail(username: String, user: User): UserDetail {
        return UserDetail (
            id = user.id!!,
            username = username,
            password = user.password,
            active = user.active,
            authorities = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
        )
    }
}