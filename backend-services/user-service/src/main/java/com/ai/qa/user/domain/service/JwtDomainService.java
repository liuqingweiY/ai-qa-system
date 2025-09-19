package com.ai.qa.user.domain.service;

import com.ai.qa.user.api.exception.BusinessException;
import com.ai.qa.user.api.exception.ResponseCodeEnum;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtDomainService {
    /**
     * 生成token
     *
     * @param username 用户名
     * @return token
     */
    public String generateToken(String username, String secret, long validTime) {
        // 生成Key
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 生成Token
        return JWT.create()
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis() + +1000 * 60 * validTime))
            .withClaim("username", username)
            .sign(algorithm);
    }

    /**
     * 校验Token
     *
     * @param token token
     * @return
     *
     */
    public void verifyToken(String  token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
        } catch (JWTDecodeException jwtDecodeException) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.TOKEN_INVALID.getMessage());
        } catch (SignatureVerificationException signatureVerificationException) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.TOKEN_SIGNATURE_INVALID.getMessage());
        } catch (TokenExpiredException tokenExpiredException) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.TOKEN_EXPIRED.getMessage());
        } catch (Exception ex) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ResponseCodeEnum.UNKNOWN_ERROR.getMessage());
        }
    }

    /**
     * 从token取得用户名
     *
     * @param token token
     * @return 用户名
     *
     */
    public String extractUserName(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("username").asString();
    }

}
