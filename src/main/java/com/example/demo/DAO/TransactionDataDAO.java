package com.example.demo.DAO;

import com.example.demo.Entity.TempEntity.GroupCodesOfClient;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class TransactionDataDAO {


    String url = "jdbc:postgresql://localhost:5432/pfm";
    String name = "postgres";
    String pass = "admin";


    public double monthlyExpenses(Long id){
        String sql = "select \n" +
                "\tclient_id, \n" +
                "\tSUM(CAST(replace(sum, ',','.') as float8))*(-1) summary\n" +
                "from pfm_transaction_data \n" +
                "where CAST(replace(sum, ',','.') as float8) < 0 \n" +
                "and client_id = ? \n" +
                "GROUP BY client_id";
        double monthlyExpenses = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, name, pass);
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet r = ps.executeQuery();
            if(r.next()){
                monthlyExpenses = r.getDouble("summary");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            try {
                if(con != null)
                    con.close();
                if(ps != null)
                    ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return monthlyExpenses;
    }


    public List<GroupCodesOfClient> findGroupsCodeOfClient(Long id){

        String sql = "select \n" +
                "\tptd.client_id client_id, \n" +
                "\tCOUNT(ptd.mcc_code) \"count\",\n" +
                "\tSUM(CAST(replace(sum, ',','.') as float8) ) *(-1) summary, \n" +
                "\tpm.group_code group_code\n" +
                "from pfm_transaction_data ptd\n" +
                "join pfm_mcc pm ON pm.code = ptd.mcc_code\n" +
                "where CAST(replace(sum, ',','.') as float8) < 0 \n" +
                "and ptd.client_id = ?\n" +
                "group by pm.group_code, ptd.client_id\n" +
                "order by summary desc\n" +
                "limit 3";

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, name, pass);
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet r = ps.executeQuery();
            List<GroupCodesOfClient> list = new LinkedList<>();
            while(r.next()){
                GroupCodesOfClient groupCodesOfClient = new GroupCodesOfClient();
                groupCodesOfClient.setClient_id(r.getLong("client_id"));
                groupCodesOfClient.setCountOfCode(r.getInt("count"));
                groupCodesOfClient.setGroupCode(r.getString("group_code"));
                groupCodesOfClient.setSummary((int) r.getDouble("summary"));
                list.add(groupCodesOfClient);
            }
            return list;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            try {
                if(con != null)
                    con.close();
                if(ps != null)
                    ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;

    }



}
