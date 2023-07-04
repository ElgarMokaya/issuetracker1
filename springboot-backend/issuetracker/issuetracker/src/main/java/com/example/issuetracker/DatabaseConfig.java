package com.example.issuetracker;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/tracking_db")
                .username("elgar")
                .password("bosibori")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

}
