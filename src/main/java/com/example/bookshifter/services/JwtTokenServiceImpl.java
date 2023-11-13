package com.example.bookshifter.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.bookshifter.entities.User;
import com.example.bookshifter.exceptions.JWTExcepion;
import com.example.bookshifter.services.interfaces.JwtTokenService;
import com.example.bookshifter.utils.TokensExpirationTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {
    //Pega o valor do application.yml e atribui a variável secret
    @Value("${api.security.token.secret}")
    private String secret;
    private String issuer = "Bookshifter";
    @Override
    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer(issuer).withSubject(user.getEmail())
                    .withExpiresAt(TokensExpirationTime.getJWTExpirationTime())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new JWTExcepion("Erro ao gerar o token:  " + exception);
        }
    }

    @Override
    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer(issuer)
                    .build().verify(token).getSubject();
        } catch (JWTVerificationException exception){
            //Este retorno significa que não há token.
            return "";
        }
    }
}
