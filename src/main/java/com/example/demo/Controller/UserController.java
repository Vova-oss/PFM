package com.example.demo.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/user")
public class UserController {

    @PostMapping("/registration")
    public void registration(){

    }

}
