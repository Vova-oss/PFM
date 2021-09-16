package com.example.demo.Service;

import com.example.demo.AuxiliaryClasses.StaticMethods;
import com.example.demo.DAO.TransactionDataDAO;
import com.example.demo.Entity.TempEntity.GroupCodesOfClient;
import com.example.demo.Entity.UserEntity;
import com.example.demo.ResponsesForWidgets.historyOfOperations.HistoryOfOperations;
import com.example.demo.ResponsesForWidgets.monthlyExpensesAndTopThreeCategories.MonthlyExpensesAndTopThreeCategories;
import com.example.demo.ResponsesForWidgets.monthlyExpensesAndTopThreeCategories.TopThreeCategories;
import com.example.demo.ResponsesForWidgets.expensesByDayOrMonth.Amount;
import com.example.demo.ResponsesForWidgets.expensesByDayOrMonth.ExpensesByDay;
import com.example.demo.ResponsesForWidgets.expensesPerWeekOrMonthByCategory.ExpensesPerWeekByCategory;
import com.example.demo.ResponsesForWidgets.expensesPerWeekOrMonthByCategory.Indicators;
import com.example.demo.Security.SService.JWTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedList;
import java.util.List;

import static com.example.demo.Security.SecurityConstants.*;

@Service
public class TransactionDataService {

    @Autowired
    JWTokenService jwTokenService;
    @Autowired
    UserService userService;

    @Autowired
    TransactionDataDAO transactionDataDAO;

//    /**
//     * Получение трат за месяц
//     * @code 432 - Incorrect JWToken
//     * @code 200 - ${monthlyExpenses}
//     */
//    public void monthlyExpenses(HttpServletRequest request, HttpServletResponse response)  {
//
//        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
//        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)){
//            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
//            if(userEntity == null)
//                return;
//
//            double allSum = transactionDataDAO.monthlyExpenses(userEntity.getId());
//            StaticMethods.createResponse(request, response, 200, String.valueOf((int) allSum));
//            return;
//
//        }
//        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
//
//    }


    /**
     * Получение трат за месяц + Топ 3 категории за месяц
     * @code 432 - Incorrect JWToken
     */
    public MonthlyExpensesAndTopThreeCategories monthlyExpensesAndTopThreeCategories(
            HttpServletRequest request, HttpServletResponse response) {

        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)){
            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
            if(userEntity == null)
                return null;

            List<GroupCodesOfClient> list = transactionDataDAO.findGroupsCodeOfClient(userEntity.getId());
            if(list == null){
                StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
                return null;
            }

            int wholeSum = (int) transactionDataDAO.monthlyExpenses(userEntity.getId());
            List<TopThreeCategories> responseList = new LinkedList<>();
            for(GroupCodesOfClient groupCodesOfClient: list){
                TopThreeCategories topThreeCategories = new TopThreeCategories();
                topThreeCategories.setCategory(groupCodesOfClient.getGroupCode());
                topThreeCategories.setPrice(String.valueOf(groupCodesOfClient.getSummary()));
                topThreeCategories.setPercent(String.valueOf((int)((double)groupCodesOfClient.getSummary()/wholeSum*100)));
                responseList.add(topThreeCategories);
            }

            MonthlyExpensesAndTopThreeCategories monthlyExpensesAndTopThreeCategories = new MonthlyExpensesAndTopThreeCategories();
            monthlyExpensesAndTopThreeCategories.setList(responseList);
            monthlyExpensesAndTopThreeCategories.setWholeSum(wholeSum);
            return monthlyExpensesAndTopThreeCategories;

        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
        return null;
    }


    /**
     * График расходов по дням
     * @code 432 - Incorrect JWToken
     */
    public ExpensesByDay expensesByDay(HttpServletRequest request, HttpServletResponse response) {

        // Получение номера недели в текущем году
//        System.out.println(new GregorianCalendar(2021, Calendar.AUGUST,1).get(Calendar.WEEK_OF_YEAR));

        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)) {
            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
            if (userEntity == null)
                return null;

            List<Amount> currentAmount = transactionDataDAO.currentOrAverageAmount(userEntity.getId(), "currentByWeek");
            List<Amount> averageAmount = transactionDataDAO.currentOrAverageAmount(userEntity.getId(), "averageByMonth");

            ExpensesByDay expensesByDay = new ExpensesByDay();
            expensesByDay.setAverageAmount(averageAmount);
            expensesByDay.setCurrentAmount(currentAmount);

            int max = 0;
            for(Amount amount:currentAmount){
                max = amount.getSum()>max?amount.getSum():max;
            }
            for(Amount amount:averageAmount){
                max = amount.getSum()>max?amount.getSum():max;
            }

            expensesByDay.setMaxAmount(max);
            return expensesByDay;
        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
        return null;
    }


    /**
     * График расходов за месяц
     * @code 432 - Incorrect JWToken
     */
    public List<Amount> expensesByMonth(HttpServletRequest request, HttpServletResponse response) {
        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)) {
            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
            if (userEntity == null)
                return null;

            return transactionDataDAO.expensesByMonth(userEntity.getId());
        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
        return null;
    }


    /**
     * Диаграмма недельных расходов по категориям
     * @code 432 - Incorrect JWToken
     */
    public ExpensesPerWeekByCategory expensesPerWeekByCategory(HttpServletRequest request, HttpServletResponse response) {

        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)) {
            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
            if (userEntity == null)
                return null;

            List<Indicators> monthlyAverages = transactionDataDAO.calculatingMonthlyAverages(userEntity.getId());
            List<String> strings = new LinkedList<>();
            for(Indicators indicators: monthlyAverages)
                strings.add(indicators.getCategory());
            List<Indicators> currentIndicators = transactionDataDAO.calculatingCurrentAmount(userEntity.getId(), strings);

            ExpensesPerWeekByCategory expensesPerWeekByCategory = new ExpensesPerWeekByCategory();
            expensesPerWeekByCategory.setMonthlyAverages(monthlyAverages);
            expensesPerWeekByCategory.setCurrentIndicators(currentIndicators);
            return expensesPerWeekByCategory;

        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
        return null;

    }


    /**
     * Топ расходов за месяц
     * @code 432 - Incorrect JWToken
     */
    public List<Indicators> topExpensesForTheMonth(HttpServletRequest request, HttpServletResponse response) {

        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)) {
            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
            if (userEntity == null)
                return null;

            return transactionDataDAO.topExpensesForTheMonth(userEntity.getId());

        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
        return null;
    }


    public List<HistoryOfOperations> historyOfOperations(
            String minSum,
            String maxSum,
            String from,
            String to,
            String operationType,
            String page,
            HttpServletRequest request,
            HttpServletResponse response) {

        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)) {
            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
            if (userEntity == null)
                return null;
            String dopRules = "";
            switch (operationType){
                case "all":
                    dopRules += "and ((CAST(replace(ptd.sum, ',','.') as float8) > "+minSum+" and CAST(replace(sum, ',','.') as float8) < "+maxSum+")\n" +
                        "or(CAST(replace(ptd.sum, ',','.') as float8) < -"+minSum+" and CAST(replace(sum, ',','.') as float8) > -"+maxSum+"))";
                    break;
                case "input":
                    dopRules += "and (CAST(replace(ptd.sum, ',','.') as float8) > "+minSum+" and CAST(replace(sum, ',','.') as float8) < "+maxSum+")";
                    break;
                case "output":
                    dopRules += "and (CAST(replace(ptd.sum, ',','.') as float8) < -"+minSum+" and CAST(replace(sum, ',','.') as float8) > -"+maxSum+")";
                    break;
                default:
                    StaticMethods.createResponse(request, response, 433, "Incorrect operationType");
                    return null;
            }

            dopRules += "and to_date(ptd.date,'DD.MM.YYYY') >= '"+from+"'\n" +
                    "and to_date(ptd.date,'DD.MM.YYYY') < '"+to+"' ";

            String limitOffset = "limit 10 offset " + (10 * Integer.parseInt(page));

            return transactionDataDAO.historyOfOperations(userEntity.getId(), dopRules, limitOffset);

        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
        return null;

    }
}
