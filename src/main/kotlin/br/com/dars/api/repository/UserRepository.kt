package br.com.dars.api.repository

import br.com.dars.api.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(username: String?): Optional<User>
    fun findByUsername(username: String?): Optional<User>
}