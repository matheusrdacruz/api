package br.com.dars.api.security

import br.com.dars.api.dto.LoginForm
import br.com.dars.api.dto.LoginResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val manager: AuthenticationManager,
    private val tokenService: TokenService
) {


    fun login(loginForm: LoginForm): LoginResponse {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginForm.username, loginForm.password)
        val authentication = manager.authenticate(authenticationToken).principal as UserDetail
        // milis * segunds * hours
        val expiresIn: Long = 60 * 60 * 24
        val tokenJWT = tokenService.generateToken(authentication, expiresIn)
        return LoginResponse(tokenJWT, expiresIn)
    }

}