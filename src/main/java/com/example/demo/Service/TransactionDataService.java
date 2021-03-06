package com.example.demo.Service;

import com.example.demo.AuxiliaryClasses.StaticMethods;
import com.example.demo.DAO.TransactionDataDAO;
import com.example.demo.DTO.ProductsOfBankDTO;
import com.example.demo.Entity.ProductsOfBank;
import com.example.demo.Entity.TempEntity.GroupCodesOfClient;
import com.example.demo.Entity.UserEntity;
import com.example.demo.ResponsesForWidgets.advertisingProducts.AnalyticsClass;
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
import java.util.stream.Stream;

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
    public List<Indicators> topExpensesForTheMonth (HttpServletRequest request, HttpServletResponse response) {

        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)) {
            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
            if (userEntity == null)
                return null;

            List<Indicators> list = transactionDataDAO.topExpensesForTheMonth(userEntity.getId());
            if(list.size()>8){
                int otherSum = 0;
                for(int i = 7; i < list.size(); i++){
                    otherSum += list.get(i).getSummary();
                }
                list = list.subList(0,7);
                list.add(new Indicators("Другое", otherSum));
            }

            return list;

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
                    dopRules += " and ((CAST(replace(ptd.sum, ',','.') as float8) > "+minSum+" and CAST(replace(sum, ',','.') as float8) < "+maxSum+")\n" +
                        "or(CAST(replace(ptd.sum, ',','.') as float8) < -"+minSum+" and CAST(replace(sum, ',','.') as float8) > -"+maxSum+"))";
                    break;
                case "input":
                    dopRules += " and (CAST(replace(ptd.sum, ',','.') as float8) > "+minSum+" and CAST(replace(sum, ',','.') as float8) < "+maxSum+")";
                    break;
                case "output":
                    dopRules += " and (CAST(replace(ptd.sum, ',','.') as float8) < -"+minSum+" and CAST(replace(sum, ',','.') as float8) > -"+maxSum+")";
                    break;
                default:
                    StaticMethods.createResponse(request, response, 433, "Incorrect operationType");
                    return null;
            }

            dopRules += " and to_date(ptd.date,'DD.MM.YYYY') >= to_date('"+from+"', 'DD.MM.YYYY') "+
                    "and to_date(ptd.date,'DD.MM.YYYY') < to_date('"+to+"', 'DD.MM.YYYY') ";


            String limitOffset = "limit 10 offset " + (10 * Integer.parseInt(page));
            return transactionDataDAO.historyOfOperations(userEntity.getId(), dopRules, limitOffset);

        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
        return null;

    }



    public ProductsOfBankDTO advertisingProducts(HttpServletRequest request, HttpServletResponse response) {


        String tokenWithPrefix = request.getHeader(HEADER_JWT_STRING);
        if(tokenWithPrefix != null && tokenWithPrefix.startsWith(TOKEN_PREFIX)) {
            UserEntity userEntity = userService.findByJWToken(tokenWithPrefix, request, response);
            if (userEntity == null)
                return null;

            List<AnalyticsClass> list = transactionDataDAO.advertisingProducts(userEntity.getId());
            int max = 0;
            double cashSum = 0;
            int maxOfCashCategory = 0;
            for(AnalyticsClass row: list){
                max = row.getSum();
                if(row.getGroup_code().equals("Автомобильные сервисы")
                    || row.getGroup_code().equals("Аптеки и медицина")
                    || row.getGroup_code().equals("Транспорт и путешествия")){
                    maxOfCashCategory += row.getSum();
                    cashSum += (float)row.getSum()/100*7;
                }
                if(row.getGroup_code().equals("Дом и ремонт")
                    || row.getGroup_code().equals("Кафе, рестораны")){
                    maxOfCashCategory += row.getSum();
                    cashSum += (float)row.getSum()/100*5;
                }
            }

            if(max < 10_000){
                ProductsOfBank productsOfBank = new ProductsOfBank();
                productsOfBank.setDescription("Карта с бесплатным обслуживание при покупках по карте от 5000 ₽ в месяц");
                productsOfBank.setLink("https://ib.psbank.ru/store/products/your-cashback-new");
                productsOfBank.setTitle("Дебетовая карта \"Твой кешбэк\"");
                return ProductsOfBankDTO.createProductsOfBankDTO(productsOfBank);
            }

            ProductsOfBank productsOfBank = new ProductsOfBank();
            if(maxOfCashCategory>= 10_000 && cashSum >= 1000){
                productsOfBank.setDescription("Прибыльная кредитная карта c кешбэком до 11%");
                productsOfBank.setLink("https://ib.psbank.ru/store/products/double-cashback");
                productsOfBank.setTitle("Двойной кешбэк");
            }else{
                productsOfBank.setDescription("Не думайте о процентах более трёх месяцев");
                productsOfBank.setLink("https://ib.psbank.ru/store/products/sto-credit");
                productsOfBank.setTitle("Кредитная карта «100+»");
            }
            return ProductsOfBankDTO.createProductsOfBankDTO(productsOfBank);


        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
        return null;

    }
}
