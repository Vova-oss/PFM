package com.example.demo.Controller;

import com.example.demo.Entity.TransactionData;
import com.example.demo.ResponsesForWidgets.TopThreeCategories;
import com.example.demo.ResponsesForWidgets.expensesByDay.Amount;
import com.example.demo.ResponsesForWidgets.expensesByDay.ExpensesByDay;
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
import java.util.List;

@Api(tags = "TransactionData")
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/transactions")
public class TransactionDataController {

    @Autowired
    TransactionDataService transactionDataService;


    @ApiOperation(value = "Траты за месяц (нужен jwt-token)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{\n   status:...,\n   info:...,\n   path:...\n}"),
            @ApiResponse(code = 432, message = "Incorrect JWToken")
    })
    @GetMapping("/monthlyExpenses")
    public void monthlyExpenses(HttpServletRequest request, HttpServletResponse response) {
        transactionDataService.monthlyExpenses(request, response);
    }


    @ApiOperation(value = "Топ три финансово затратные категории (нужен jwt-token)")
    @ApiResponses(value = {
            @ApiResponse(code = 432, message = "Incorrect JWToken")
    })
    @GetMapping("/topThreeCategories")
    public List<TopThreeCategories> topThreeCategories(HttpServletRequest request, HttpServletResponse response){
        return transactionDataService.topThreeCategories(request, response);
    }


    @ApiOperation(value = "График расходов по дням (нужен jwt-token)")
    @ApiResponses(value = {
            @ApiResponse(code = 432, message = "Incorrect JWToken"),
            @ApiResponse(code = 200, message = "currentAmount - текущая сумма:\n-date - дата в формате 'DD.MM.YYYY' " +
                    "(расположил по порядку)" +
                    "\n-sum - сумма\n\taverageAmount - Средняя сумма:\n-date - день недели(на английском+в разнобой)" +
                    "\n-sum - сумма\nmaxAmount - Самое максимальное значение, которое нужно принять за 100%")
    })
    @GetMapping("/expensesByDay")
    public ExpensesByDay expensesByDay(HttpServletRequest request, HttpServletResponse response){
        return transactionDataService.expensesByDay(request, response);
    }



    @ApiOperation(value = "График расходов за месяц (нужен jwt-token)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "date - дата в формате DD.MM.YYYY\nsum - сумма"),
            @ApiResponse(code = 432, message = "Incorrect JWToken")
    })
    @GetMapping("/expensesByMonth")
    public List<Amount> expensesByMonth(HttpServletRequest request, HttpServletResponse response){
        return transactionDataService.expensesByMonth(request, response);
    }

}
