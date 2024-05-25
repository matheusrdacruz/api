package br.com.dars.api.security

import br.com.dars.api.exceptions.TokenGenerateException
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.time.Instant


@Service
class TokenService {

    @Value("\${jwt.public.key}")
    private val rsaPublicKey: RSAPublicKey? = null

    @Value("\${jwt.private.key}")
    private val rsaPrivateKey: RSAPrivateKey? = null
    private val ISSUER_NAME = "API voll.med"

    fun generateToken(user: UserDetail, experisIn: Long): String {
        val scopes = "admin"
        /*        val scopeT = user.roles
        //            .stream()
        //            .map(Role::name)
        //            .collect(Collectors.joining(" "));*/
        try {
            val algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey)
            val now = Instant.now()
            val token = JWT.create()
                .withIssuer(ISSUER_NAME)
                .withSubject(user.username)
                .withClaim("id", user.id)
                .withClaim("scope", scopes)
                .withIssuedAt(now)
                .withExpiresAt(now.plusSeconds(experisIn))
                .sign(algorithm)
            return token
        } catch (exception: JWTCreationException) {
            throw TokenGenerateException("Token generation failed: ${exception.message}")
        }
    }

    fun getSubject(token: String): String {
        try {
            val algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey)
            return JWT.require(algorithm) // specify any specific claim validations
                .withIssuer(ISSUER_NAME) // reusable verifier instance
                .build().verify(token).subject
        } catch (exception: JWTVerificationException) {
            throw TokenGenerateException("Token verification failed: ${exception.message}")
        }
    }

}