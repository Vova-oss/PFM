package com.example.demo.Service;

import com.example.demo.AuxiliaryClasses.StaticMethods;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Security.SService.JWTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import static com.example.demo.Security.SecurityConstants.*;

@Service
public class TransactionDataService {

    @Autowired
    JWTokenService jwTokenService;
    @Autowired
    UserService userService;

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
            String token = tokenWithPrefix.replace(TOKEN_PREFIX, "");
            String login = jwTokenService.getLoginFromJWT(token);
            if(login == null){
                StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");
                return;
            }

            UserEntity userEntity = userService.findByLogin(login);

            double monthlyExpenses = 0;
            Connection con = null;
            Statement st = null;
            try {
                con = DriverManager.getConnection(url, name, pass);
                st = con.createStatement();
                ResultSet r = st.executeQuery("SELECT * FROM pfm_transaction_data WHERE client_id = " + userEntity.getId());
                while(r.next()){
                    if(Double.parseDouble(r.getString("sum").replace(",",".")) < 0){
                        monthlyExpenses += Double.parseDouble(r.getString("sum").replace(",","."))*(-1);
                    }
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }finally {
                try {
                    if(con != null)
                        con.close();
                    if(st != null)
                        st.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            StaticMethods.createResponse(request, response, 200, String.valueOf((int) monthlyExpenses));
            return;

        }
        StaticMethods.createResponse(request, response, 432, "Incorrect JWToken");

    }



}
