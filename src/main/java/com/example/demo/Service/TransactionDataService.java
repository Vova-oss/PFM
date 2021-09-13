package com.example.demo.Service;

import com.example.demo.AuxiliaryClasses.StaticMethods;
import com.example.demo.DAO.TransactionDataDAO;
import com.example.demo.Entity.TempEntity.GroupCodesOfClient;
import com.example.demo.Entity.UserEntity;
import com.example.demo.ResponsesForWidgets.TopThreeCategories;
import com.example.demo.ResponsesForWidgets.expensesByDayOrMonth.Amount;
import com.example.demo.ResponsesForWidgets.expensesByDayOrMonth.ExpensesByDay;
import com.example.demo.ResponsesForWidgets.expensesPerWeekByCategory.ExpensesPerWeekByCategory;
import com.example.demo.ResponsesForWidgets.expensesPerWeekByCategory.Indicators;
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

    /**
     * Получение трат за месяц
     * @code 432 - Incorrect JWToken
     * @code 200 - ${monthlyExpenses}
     */
    public void monthlyExpenses(HttpServletRequest request, HttpServletResponse response)  {

        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)){
            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
            if(userEntity == null)
                return;

            double allSum = transactionDataDAO.monthlyExpenses(userEntity.getId());
            StaticMethods.createResponse(request, response, 200, String.valueOf((int) allSum));
            return;

        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");

    }


    public List<TopThreeCategories> topThreeCategories(HttpServletRequest request, HttpServletResponse response) {

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

            int allSum = (int) transactionDataDAO.monthlyExpenses(userEntity.getId());
            List<TopThreeCategories> responseList = new LinkedList<>();
            for(GroupCodesOfClient groupCodesOfClient: list){
                TopThreeCategories topThreeCategories = new TopThreeCategories();
                topThreeCategories.setCategory(groupCodesOfClient.getGroupCode());
                topThreeCategories.setPrice(String.valueOf(groupCodesOfClient.getSummary()));
                topThreeCategories.setPercent(String.valueOf((int)((double)groupCodesOfClient.getSummary()/allSum*100)));
                responseList.add(topThreeCategories);
            }

            return responseList;

        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
        return null;
    }

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
}
