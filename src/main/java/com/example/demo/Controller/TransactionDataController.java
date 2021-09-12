package com.example.demo.Controller;

import com.example.demo.Entity.TransactionData;
import com.example.demo.Service.TransactionDataService;
import com.example.demo.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Api(tags = "TransactionData")
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/transactions")
public class TransactionDataController {

    @Autowired
    TransactionDataService transactionDataService;


    @ApiOperation(value = "Траты за месяц (нужен только jwt-token)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\n   status:...,\n   info:...,\n   path:...\n}"),
            @ApiResponse(code = 432, message = "Incorrect JWToken")
    })
    @GetMapping("/monthlyExpenses")
    public void monthlyExpenses(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        transactionDataService.monthlyExpenses(request, response);
    }

}
