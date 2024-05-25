package br.com.dars.api.config

import br.com.dars.api.entities.User
import br.com.dars.api.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class AdminUserConfig (
    private val userService: UserService
) : CommandLineRunner {
    private val logger = LoggerFactory.getLogger(AdminUserConfig::class.java)
    private val ADMIN = "admin"
    override fun run(vararg args: String?) {
        val user = userService.findByUsername(ADMIN)


        if (user.isEmpty) {
            val newUser = User(
                name = ADMIN,
                username = ADMIN,
                password = ADMIN,
                email = "admin@arkve.com.br",
                active = true
            )
            logger.info("CRIANDO ADMIN: $newUser")
            userService.save(newUser)
        }
    }


}