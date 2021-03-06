package com.example.demo.DAO;

import com.example.demo.Entity.TempEntity.GroupCodesOfClient;
import com.example.demo.ResponsesForWidgets.advertisingProducts.AnalyticsClass;
import com.example.demo.ResponsesForWidgets.expensesByDayOrMonth.Amount;
import com.example.demo.ResponsesForWidgets.expensesPerWeekOrMonthByCategory.Indicators;
import com.example.demo.ResponsesForWidgets.historyOfOperations.HistoryOfOperations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class TransactionDataDAO {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String name;
    @Value("${spring.datasource.password}")
    String pass;


    /** Получение трат за месяц */
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


    /**
     * Топ 3 категории за месяц
     * @param id - :id пользователя
     */
    public List<GroupCodesOfClient> findGroupsCodeOfClient(Long id){
// ------------------ Базовые -------------------------
//        String sql = "select \n" +
//                "\tptd.client_id client_id, \n" +
//                "\tCOUNT(ptd.mcc_code) \"count\",\n" +
//                "\tSUM(CAST(replace(sum, ',','.') as float8) ) *(-1) summary, \n" +
//                "\tpm.group_code group_code\n" +
//                "from pfm_transaction_data ptd\n" +
//                "join pfm_mcc pm ON pm.code = ptd.mcc_code\n" +
//                "where CAST(replace(sum, ',','.') as float8) < 0 \n" +
//                "and ptd.client_id = ?\n" +
//                "group by pm.group_code, ptd.client_id\n" +
//                "order by summary desc\n" +
//                "limit 3";

        String sql = "select \n" +
                "\tptd.client_id client_id, \n" +
                "\tCOUNT(ptd.mcc_code) \"count\",\n" +
                "\tSUM(CAST(replace(sum, ',','.') as float8) ) *(-1) summary, \n" +
                "\tpm.group_code_rus group_code\n" +
                "from pfm_transaction_data ptd\n" +
                "join pfm_our_mcc pm ON pm.code = ptd.mcc_code\n" +
                "where CAST(replace(sum, ',','.') as float8) < 0 \n" +
                "and ptd.client_id = ?\n" +
                "group by pm.group_code_rus, ptd.client_id\n" +
                "order by summary desc\n" +
                "limit 3;";

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


    /**
     * В графике расходов по дням ищет Среднюю сумму или Текущую сумму.
     * Средняя сумма - среднее значение расходов в месяц за определённый день недели.
     * Текущая сумма - значение расходов за данный день недели на текущей недели.
     * @param id - :id пользователя
     * @param sql - строка принимает одно из двух значений:
     *            averageByMonth - если хотим посчитать Среднюю сумму
     *            currentByWeek - если хотим посчитать Текущую сумму
     */
    public List<Amount> currentOrAverageAmount(Long id, String sql) {

        String currentByWeek = "select \n" +
                "\txxx.date \"date\"\n" +
                "    , coalesce(SUM(CAST(replace(sum, ',','.') as float8) ) *(-1), 0) summary\n" +
                "from pfm_transaction_data ptd\n" +
                "right join \n" +
                "(\n" +
                "\tselect to_char(to_date(CAST(generate_series('2021-08-20', '2021-09-30', '1 day'::interval) as text), 'YYYY-MM-DD'),'DD.MM.YYYY') \"date\"\n" +
                ") xxx on to_date(xxx.\"date\", 'DD.MM.YYYY')= to_date(ptd.date,'DD.MM.YYYY')\n" +
                "where to_char(to_date(xxx.\"date\",'DD.MM.YYYY'), 'IW') = '35'\n" +
                "and (CAST(replace(sum, ',','.') as float8) < 0 or CAST(replace(sum, ',','.') as float8) is NULL)\n" +
                "and (ptd.client_id = ? or ptd.client_id is NULL)\n" +
                "group by xxx.\"date\"" +
                "order by to_date(xxx.\"date\",'DD.MM.YYYY');";

        String averageByMonth = "select \n" +
                "\tyyy.days \"date\",\n" +
                "\tcoalesce(AVG(xxx.summary), 0) summary\n" +
                "from(\n" +
                "\tselect \n" +
                "\t\tto_date(ptd.date,'DD.MM.YYYY'),\n" +
                "\t\tSUM(CAST(replace(sum, ',','.') as float8) ) *(-1) summary\n" +
                "\tfrom pfm_transaction_data ptd\n" +
                "\twhere ptd.client_id = ?\n" +
                "\tand to_char(to_date(ptd.date,'DD.MM.YYYY'), 'YYYY') = '2021'\n" +
                "\tand to_char(to_date(ptd.date,'DD.MM.YYYY'), 'MM') = '08'\n" +
                "\tand CAST(replace(sum, ',','.') as float8) < 0\n" +
                "\tgroup by to_date(ptd.date,'DD.MM.YYYY')\n" +
                "\n" +
                ") xxx \n" +
                "right join \n" +
                "(\n" +
                "\tselect CAST(generate_series(1, 7) as text) days\n" +
                ") yyy on yyy.days = to_char(xxx.to_date, 'ID')\n" +
                "group by yyy.days;";
        if(sql.equals("averageByMonth"))
            sql = averageByMonth;
        if(sql.equals("currentByWeek"))
            sql = currentByWeek;

        List<Amount> currentAmount = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, name, pass);
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet r = ps.executeQuery();
            currentAmount = new LinkedList<>();
            while (r.next()){
                Amount amount = new Amount();
                amount.setData(r.getString("date"));
                amount.setSum((int)r.getDouble("summary"));
                currentAmount.add(amount);
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
        return currentAmount;
    }


    /**
     *
     * @param id
     * @return
     */
    public List<Amount> expensesByMonth(Long id){

        String sql = "select \n" +
                "\tgd.\"date\"\n" +
                "\t, coalesce(mp.summary*(-1), 0) summary\n" +
                "from \n" +
                "(\n" +
                "\tselect to_char(to_date(CAST(generate_series('2021-08-01', '2021-08-31', '1 day'::interval) as text), 'YYYY-MM-DD'),'DD.MM.YYYY') \"date\"\n" +
                ") gd\n" +
                "left join \n" +
                "(\n" +
                "\tselect DISTINCT\n" +
                "\t\tptd.date \n" +
                "\t\t, SUM(CAST(replace(sum, ',','.') as float8)) summary\n" +
                "\tfrom pfm_transaction_data ptd\n" +
                "\twhere CAST(replace(sum, ',','.') as float8) < 0 \n" +
                "\tand ptd.client_id = ?\n" +
                "\tand to_char(to_date(ptd.date,'DD.MM.YYYY'), 'YYYY') = '2021'\n" +
                "\tand to_char(to_date(ptd.date,'DD.MM.YYYY'), 'MM') = '08'\n" +
                "\tgroup by ptd.date\n" +
                ") mp on mp.date = gd.\"date\"";

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, name, pass);
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet r = ps.executeQuery();
            List<Amount> list = new LinkedList<>();
            while(r.next()){
                Amount amount = new Amount();
                amount.setData(r.getString("date"));
                amount.setSum((int) r.getDouble("summary"));
                list.add(amount);
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

    public List<Indicators> calculatingMonthlyAverages(Long id) {

// -------------- Базовые --------------------
//        String sql = "select * from\n" +
//                "(\n" +
//                "\tselect \n" +
//                "\t\txxx.group_code group_code\n" +
//                "\t\t, AVG(xxx.summary) summary\n" +
//                "\tfrom(\n" +
//                "\t\tselect \n" +
//                "\t\t\tpm.group_code group_code\n" +
//                "\t\t\t, to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'IW') \"date\"\n" +
//                "\t\t\t, coalesce(SUM(CAST(replace(sum, ',','.') as float8) ) *(-1), 0) summary\n" +
//                "\t\tfrom pfm_transaction_data ptd\n" +
//                "\t\tjoin pfm_mcc pm on pm.code = ptd.mcc_code\n" +
//                "\t\twhere to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'YYYY') = '2021'\n" +
//                "\t\tand to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'MM') = '08'\n" +
//                "\t\tand CAST(replace(sum, ',','.') as float8) < 0 \n" +
//                "\t\tand ptd.client_id = ? \n" +
//                "\t\tgroup by to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'IW'), pm.group_code\n" +
//                "\t) xxx\n" +
//                "\tgroup by xxx.group_code\n" +
//                "\torder by summary desc\n" +
//                "\tlimit 7\n" +
//                ") yyy\n" +
//                "order by group_code";

        String sql = "select * from\n" +
                "(\n" +
                "\tselect \n" +
                "\t\txxx.group_code group_code\n" +
                "\t\t, AVG(xxx.summary) summary\n" +
                "\tfrom(\n" +
                "\t\tselect \n" +
                "\t\t\tpm.group_code_rus group_code\n" +
                "\t\t\t, to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'IW') \"date\"\n" +
                "\t\t\t, coalesce(SUM(CAST(replace(sum, ',','.') as float8) ) *(-1), 0) summary\n" +
                "\t\tfrom pfm_transaction_data ptd\n" +
                "\t\tjoin pfm_our_mcc pm on pm.code = ptd.mcc_code\n" +
                "\t\twhere to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'YYYY') = '2021'\n" +
                "\t\tand to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'MM') = '08'\n" +
                "\t\tand CAST(replace(sum, ',','.') as float8) < 0 \n" +
                "\t\tand ptd.client_id = ? \n" +
                "\t\tgroup by to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'IW'), pm.group_code_rus\n" +
                "\t) xxx\n" +
                "\tgroup by xxx.group_code\n" +
                "\torder by summary desc\n" +
                "\tlimit 7\n" +
                ") yyy\n" +
                "order by group_code;";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, name, pass);
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet r = ps.executeQuery();
            List<Indicators> list = new LinkedList<>();
            while(r.next()){
                Indicators indicators = new Indicators();
                indicators.setCategory(r.getString("group_code"));
                indicators.setSummary((int) r.getDouble("summary"));
                list.add(indicators);
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

    public List<Indicators> calculatingCurrentAmount(Long id, List<String> strings){

// --------------------- Базовый -------------------
//        String sql = "select  DISTINCT\n" +
//                "\tpm.group_code\n" +
//                "\t, coalesce(xxx.summary,0) summary\n" +
//                "from pfm_mcc pm \n" +
//                "left join(\n" +
//                "\n" +
//                "select \n" +
//                "\tpm.group_code\n" +
//                "\t, coalesce(SUM(CAST(replace(sum, ',','.') as float8) ) *(-1), 0) summary\n" +
//                "from pfm_transaction_data ptd\n" +
//                "join pfm_mcc pm on pm.code = ptd.mcc_code\n" +
//                "where to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'YYYY') = '2021'\n" +
//                "and to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'MM') = '08'\n" +
//                "and to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'IW') = '34'\n" +
//                "and CAST(replace(sum, ',','.') as float8) < 0 \n" +
//                "and ptd.client_id = ? \n" +
//                "\n" +
//                "group by pm.group_code\n" +
//                "order by summary desc\n" +
//                ") xxx on xxx.group_code = pm.group_code\n" +
//                "\n" +
//                "where (pm.group_code = ? \n" +
//                "\tor pm.group_code = ?\n" +
//                "\tor pm.group_code = ?\n" +
//                "\tor pm.group_code = ?\n" +
//                "\tor pm.group_code = ?\n" +
//                "\tor pm.group_code = ?\n" +
//                "\tor pm.group_code = ?)\n" +
//                "order by pm.group_code";

        String sql = "select  DISTINCT\n" +
                "\tpm.group_code_rus group_code\n" +
                "\t, coalesce(xxx.summary,0) summary\n" +
                "from pfm_our_mcc pm \n" +
                "left join(\n" +
                "\n" +
                "\tselect \n" +
                "\t\tpm.group_code_rus group_code\n" +
                "\t\t, coalesce(SUM(CAST(replace(sum, ',','.') as float8) ) *(-1), 0) summary\n" +
                "\tfrom pfm_transaction_data ptd\n" +
                "\tjoin pfm_our_mcc pm on pm.code = ptd.mcc_code\n" +
                "\twhere to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'YYYY') = '2021'\n" +
                "\tand to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'MM') = '08'\n" +
                "\tand to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'IW') = '34'\n" +
                "\tand CAST(replace(sum, ',','.') as float8) < 0 \n" +
                "\tand ptd.client_id = ? \n" +
                "\n" +
                "\tgroup by pm.group_code_rus\n" +
                "\torder by summary desc\n" +
                ") xxx on xxx.group_code = pm.group_code_rus\n" +
                "\n" +
                "where (pm.group_code_rus = ?\n" +
                "\tor pm.group_code_rus = ?\n" +
                "\tor pm.group_code_rus = ?\n" +
                "\tor pm.group_code_rus = ?\n" +
                "\tor pm.group_code_rus = ?\n" +
                "\tor pm.group_code_rus = ?\n" +
                "\tor pm.group_code_rus = ?)\n" +
                "order by pm.group_code_rus;";


        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, name, pass);
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);

            for (int i = 0; i <7; i++){
                if(strings.size()>i)
                    ps.setString(i+2, strings.get(i));
                else ps.setString(i+2, null);
            }

            ResultSet r = ps.executeQuery();
            List<Indicators> list = new LinkedList<>();
            while(r.next()){
                Indicators indicators = new Indicators();
                indicators.setCategory(r.getString("group_code"));
                indicators.setSummary((int) r.getDouble("summary"));
                list.add(indicators);
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

    public List<Indicators> topExpensesForTheMonth(Long id) {
// ----------------- Базовый ------------------
//        String sql = "select \n" +
//                "\tpm.group_code\n" +
//                "\t, SUM(CAST(replace(ptd.sum, ',','.') as float8) ) *(-1) summary\n" +
//                "from pfm_transaction_data ptd\n" +
//                "join pfm_mcc pm on pm.code = ptd.mcc_code\n" +
//                "where client_id = ?\n" +
//                "and to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'YYYY') = '2021'\n" +
//                "and to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'MM') = '08'\n" +
//                "and CAST(replace(sum, ',','.') as float8) < 0 \n" +
//                "group by pm.group_code";

        String sql = "select \n" +
                "\tpm.group_code_rus group_code\n" +
                "\t, SUM(CAST(replace(ptd.sum, ',','.') as float8) ) *(-1) summary\n" +
                "from pfm_transaction_data ptd\n" +
                "join pfm_our_mcc pm on pm.code = ptd.mcc_code\n" +
                "where client_id = ?\n" +
                "and to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'YYYY') = '2021'\n" +
                "and to_char(to_date(ptd.\"date\",'DD.MM.YYYY'), 'MM') = '08'\n" +
                "and CAST(replace(sum, ',','.') as float8) < 0 \n" +
                "group by pm.group_code_rus\n" +
                "order by summary desc;";


        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, name, pass);
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet r = ps.executeQuery();
            List<Indicators> list = new LinkedList<>();
            while(r.next()){
                Indicators indicators = new Indicators();
                indicators.setCategory(r.getString("group_code"));
                indicators.setSummary((int) r.getDouble("summary"));
                list.add(indicators);
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

    public List<HistoryOfOperations> historyOfOperations(Long id, String dopRules, String limitOffset) {


        String sql = "select \n" +
                "\tptd.info\n" +
                ",to_date(ptd.date,'DD.MM.YYYY') ttt\n"+
                "\t, ptd.currency\n" +
                "\t, CAST(replace(sum, ',','.') as float8) sum\n" +
                "\t, ptd.date\n" +
                "from pfm_transaction_data ptd \n" +
                "where ptd.client_id = ? \n"
                 +dopRules + " order by to_date(ptd.date,'DD.MM.YYYY') desc, \"sum\"  desc, ptd.info desc "+limitOffset;


        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, name, pass);
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet r = ps.executeQuery();
            List<HistoryOfOperations> list = new LinkedList<>();
            while(r.next()){
                HistoryOfOperations historyOfOperations = new HistoryOfOperations();
                historyOfOperations.setDate(r.getString("date"));
                historyOfOperations.setCurrency(r.getString("currency"));
                historyOfOperations.setInfo(r.getString("info"));
                historyOfOperations.setSum((int) r.getDouble("sum"));

                list.add(historyOfOperations);
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

    public List<AnalyticsClass> advertisingProducts(Long id) {

        String sql = "select \n" +
                "\tpom.group_code_rus group_code\n" +
                "\t, ptd.score_of_card is_credit\n" +
                "\t, SUM(CAST(replace(ptd.sum, ',','.') as float8)) sum\n" +
                "from pfm_transaction_data ptd \n" +
                "join pfm_our_mcc pom on pom.code = ptd.mcc_code_rus \n" +
                "where ptd.client_id = ?\n" +
                "and CAST(replace(ptd.sum, ',','.') as float8) < 0\n" +
                "group by pom.group_code_rus, ptd.score_of_card";


        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, name, pass);
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet r = ps.executeQuery();
            List<AnalyticsClass> list = new LinkedList<>();
            while(r.next()){
                AnalyticsClass analyticsClass = new AnalyticsClass();
                analyticsClass.setGroup_code(r.getString("group_code"));
                analyticsClass.setIs_credit(r.getString("is_credit"));
                analyticsClass.setSum((int) r.getDouble("sum"));

                list.add(analyticsClass);
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


