package com.example.demo.Security.SService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

import static com.example.demo.Security.SecurityConstants.EXPIRATION_TIME_OF_JWT;
import static com.example.demo.Security.SecurityConstants.SECRET;

@Service
public class JWTokenService {

    /** Получаем логин из токена, который нельзя верифицировать, ибо он просрочен */
    public String decodeJWT(String jwt){
        String[] chunks = jwt.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String json = new String(decoder.decode(chunks[1]));

        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString("sub");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Создаём JWT */
    public String createJWT(String login, String role){
        return JWT.create()
                .withSubject(login)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis()+ EXPIRATION_TIME_OF_JWT))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    /** Получение login из JWT */
    public String getLoginFromJWT(String token){
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }

    /** Получение роли из JWT */
    public String getRoleFromJWT(String token){
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                        .build()
                        .verify(token)
                        .getClaim("role").asString();
    }




}
