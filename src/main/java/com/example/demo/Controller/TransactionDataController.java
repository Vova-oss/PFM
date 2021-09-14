package com.example.demo.Controller;

import com.example.demo.ResponsesForWidgets.monthlyExpensesAndTopThreeCategories.MonthlyExpensesAndTopThreeCategories;
import com.example.demo.ResponsesForWidgets.expensesByDayOrMonth.Amount;
import com.example.demo.ResponsesForWidgets.expensesByDayOrMonth.ExpensesByDay;
import com.example.demo.ResponsesForWidgets.expensesPerWeekOrMonthByCategory.ExpensesPerWeekByCategory;
import com.example.demo.ResponsesForWidgets.expensesPerWeekOrMonthByCategory.Indicators;
import com.example.demo.Service.TransactionDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "TransactionData")
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/transactions")
public class TransactionDataController {

    @Autowired
    TransactionDataService transactionDataService;


//    @ApiOperation(value = "Траты за месяц (нужен jwt-token)")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "{\n   status:...,\n   info:...,\n   path:...\n}"),
//            @ApiResponse(code = 432, message = "Incorrect JWToken")
//    })
//    @GetMapping("/monthlyExpenses")
//    public void monthlyExpenses(HttpServletRequest request, HttpServletResponse response) {
//        transactionDataService.monthlyExpenses(request, response);
//    }


    @ApiOperation(value = "Траты за месяц и Топ три финансово затратные категории (нужен jwt-token)")
    @ApiResponses(value = {
            @ApiResponse(code = 432, message = "Incorrect JWToken")
    })
    @GetMapping("/monthlyExpensesAndTopThreeCategories")
    public MonthlyExpensesAndTopThreeCategories monthlyExpensesAndTopThreeCategories(HttpServletRequest request, HttpServletResponse response){
        return transactionDataService.monthlyExpensesAndTopThreeCategories(request, response);
    }


    @ApiOperation(value = "График расходов по дням (нужен jwt-token)")
    @ApiResponses(value = {
            @ApiResponse(code = 432, message = "Incorrect JWToken"),
            @ApiResponse(code = 200, message = "currentAmount - текущая сумма:\n-date - дата в формате 'DD.MM.YYYY' " +
                    "(расположил по порядку)" +
                    "\n-sum - сумма\n\taverageAmount - Средняя сумма:\n-date - день недели(его номер)" +
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


    @ApiOperation(value = "Диаграмма недельных расходов по категориям (нужен jwt-token)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "monthlyAverages - средние показатели за месяц\n" +
                    "currentIndicators - текущие показатели"),
            @ApiResponse(code = 432, message = "Incorrect JWToken")
    })
    @GetMapping("/expensesPerWeekByCategory")
    public ExpensesPerWeekByCategory expensesPerWeekByCategory(
            HttpServletRequest request,
            HttpServletResponse response){
        return transactionDataService.expensesPerWeekByCategory(request, response);
    }


    @ApiOperation(value = "Топ расходов за месяц")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "category(string) - категория\nsummary(int) - сумма"),
            @ApiResponse(code = 432, message = "Incorrect JWToken")
    })
    @GetMapping("/topExpensesForTheMonth")
    public List<Indicators> topExpensesForTheMonth(HttpServletRequest request, HttpServletResponse response){
        return transactionDataService.topExpensesForTheMonth(request, response);
    }


    @GetMapping("/historyOfOperations")
    public void historyOfOperations(
            @RequestParam(value = "minSum", required = false, defaultValue = "0") String minSum,
            @RequestParam(value = "maxSum", required = false, defaultValue = "-1") String maxSum,
            @RequestParam(value = "from", required = false, defaultValue = "01.08.2021") String from,
            @RequestParam(value = "to", required = false, defaultValue = "01.10.2021") String to,
            @RequestParam(value = "operationType", required = false, defaultValue = "all") String operationType,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            HttpServletRequest request,
            HttpServletResponse response){
        transactionDataService.historyOfOperations(
                minSum,
                maxSum,
                from,
                to,
                operationType,
                page,
                request,
                response);
    }

}
