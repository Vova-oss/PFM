package com.example.demo.Security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.demo.AuxiliaryClasses.StaticMethods;
import com.example.demo.Security.SService.JWTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.Security.SecurityConstants.HEADER_JWT_STRING;
import static com.example.demo.Security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    JWTokenService jwTokenService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTokenService jwTokenService) {
        super(authenticationManager);
        this.jwTokenService = jwTokenService;
    }

    /** Определение прав доступа пользователя */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER_JWT_STRING);
        if(header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);

        if(authentication==null)
            return;

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    /**
     * Верификация токена с дальнейшим получением данных пользователя
     * @code 468 - Token was expired
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response){
        String header = request.getHeader(HEADER_JWT_STRING);
        if(header != null){
            String token = header.replace(TOKEN_PREFIX, "");
            try {

                // Логином является телефонный номер
                String login = jwTokenService.getLoginFromJWT(token);
                String role = jwTokenService.getRoleFromJWT(token);

                // Создаём лист ролей (у нас всего одна роль)
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));

                if(login != null){
                    return new UsernamePasswordAuthenticationToken(
                            login, null,   authorities
                    );
                }
            }catch (JWTDecodeException e){
                return null;
            }catch (TokenExpiredException e){
                StaticMethods.createResponse(request, response, 468, "Token was expired");
                return null;
            }
            return null;
        }
        return null;
    }

}













