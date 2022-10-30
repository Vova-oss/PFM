package com.example.demo.Controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "User")
@RestController
@CrossOrigin("https://hack.saizaax.xyz:3000")
@RequestMapping("/user")
public class UserController {

    @Hidden
    @PostMapping("/registration")
    public void registration(){

    }

}
