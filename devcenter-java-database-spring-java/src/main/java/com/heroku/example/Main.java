package com.heroku.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;

@Component
public class Main {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class.getPackage().getName());
    }

    @PostConstruct
    public void myRealMainMethod() throws SQLException {
        Statement stmt = dataSource.getConnection().createStatement(); 
        //stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
        //stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
        //stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("select accountcode, account ,sum(cash) as amount_Sum from pos group by accountcode,account order by accountcode desc");
         
        
        while (rs.next()) {
            System.out.println("Read from DB: " + rs.getString("AccountCode")+"  " +rs.getString("Account") + " "  + rs.getInt("amount_Sum"));
        }
        
        //매장별 시간대별 매출/건수(영수증번호 기준) :  동시간대 전체 매장의 평균매출/건수
        
    }
 
}