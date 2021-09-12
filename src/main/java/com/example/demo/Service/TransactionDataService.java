package com.example.demo.Service;

import com.example.demo.AuxiliaryClasses.StaticMethods;
import com.example.demo.DAO.TransactionDataDAO;
import com.example.demo.Entity.TempEntity.GroupCodesOfClient;
import com.example.demo.Entity.UserEntity;
import com.example.demo.ResponsesForWidgets.TopThreeCategories;
import com.example.demo.Security.SService.JWTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
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

    String url = "jdbc:postgresql://localhost:5432/pfm";
    String name = "postgres";
    String pass = "admin";


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

            Integer allSum = (int) transactionDataDAO.monthlyExpenses(userEntity.getId());
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
}
