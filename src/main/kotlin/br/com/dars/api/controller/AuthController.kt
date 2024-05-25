package br.com.dars.api.controller

import br.com.dars.api.dto.LoginForm
import br.com.dars.api.dto.LoginResponse
import br.com.dars.api.security.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class AuthController (
    private val loginService: LoginService
) {
    @PostMapping
    fun login(@RequestBody loginForm: LoginForm): ResponseEntity<LoginResponse> {
        val loginResponse = loginService.login(loginForm)
        return ResponseEntity.ok(loginResponse)
    }
}