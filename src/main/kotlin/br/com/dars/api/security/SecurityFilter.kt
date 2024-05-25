package br.com.dars.api.security

import br.com.dars.api.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class SecurityFilter (
    private val tokenService: TokenService,
    private val userService: UserService
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getTokenHeader(request)
        if (token != null) {
            val subject = tokenService.getSubject(token)
            val user = userService.findUserByLogin(subject)
            val authentication = UsernamePasswordAuthenticationToken(subject, null, user?.authorities)
            SecurityContextHolder.getContext().authentication = authentication
            System.out.println("Subject: $subject")
        }
        filterChain.doFilter(request, response)
    }


    private fun getTokenHeader(request: HttpServletRequest): String? {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (StringUtils.isNotBlank(token)) {
            return token.removePrefix("Bearer ")
        }
        return null
    }
}

