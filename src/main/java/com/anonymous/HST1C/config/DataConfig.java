package com.anonymous.HST1C.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

    //NOTE:change to actual datasource when the project moves to production phase
    @Bean
    public DataSource dataSource(){
        if(true){//TODO:change this to true when move to production
            DriverManagerDataSource ds=new DriverManagerDataSource();
            ds.setDriverClassName("org.mariadb.jdbc.Driver");
            ds.setUrl("jdbc:mariadb://localhost:3306/test");
            ds.setUsername("root");
            ds.setPassword("");
            return ds;
        }else {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("classpath:schema.sql")
                    .addScript("classpath:testUser.sql")
                    .build();
        }
    }

    @Bean
    public NamedParameterJdbcOperations jdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
