package com.example.demo.Security.SController;

import com.example.demo.Security.SService.JWTokenService;
import com.example.demo.Security.SService.RefreshTokenService;
import com.example.demo.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags = "Security")
@CrossOrigin("*")
public class SecurityController {

    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    JWTokenService jwTokenService;
    @Autowired
    UserService userService;


    @ApiOperation(value = "Обновление токенов (В хедере необходимы два токена с именами ExpiredJWT и RefreshToken")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "---"),
            @ApiResponse(code = 432, message = "Refresh token doesn't exist"),
            @ApiResponse(code = 433, message = "Refresh token was expired")
    })
    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        refreshTokenService.refreshToken(request, response);
    }
}
