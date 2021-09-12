package com.example.demo.AuxiliaryClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StaticMethods {


    /**
     * Создание ответа
     * @param status - статус ответа
     * @param info - инфорация, которая будет прописана под полем "info"
     */
    public static void createResponse(HttpServletRequest request,
                                      HttpServletResponse response,
                                      int status,
                                      String info){
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status);
//        response.addHeader("Access-Control-Allow-Origin", "*" );

        final Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("info", info);
        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(response.getOutputStream(), body);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Получение конкретного поля из json
     * @param body - изначальный json
     * @param field - поле, которое необходимо получить из этого json
     * @return field/null
     *
     * @code 400 - Incorrect JSON
     */
    public static String parsingJson (String body,
                                      String field,
                                      HttpServletRequest request ,
                                      HttpServletResponse response) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            field = jsonObject.getString(field);
        } catch (JSONException e) {
            StaticMethods.createResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, "Incorrect JSON");
            return null;
        }
        return field;
    }


}
