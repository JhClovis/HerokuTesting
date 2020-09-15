package com.heroku.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.heroku.example")
public class MainConfig {

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        
        //String url = "jdbc:postgresql://localhost/postgres";
        //Properties props = new Properties();
        //props.setProperty("user","postgres");
        //props.setProperty("password","a1s2d3f4");
        //props.setProperty("ssl","true");
        //Connection conn = DriverManager.getConnection(url, props);
        //String username = "postgres";
        //String password = "a1s2d3f4";
        
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl); 
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

}