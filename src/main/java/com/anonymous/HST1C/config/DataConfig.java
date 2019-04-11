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
        if(false){//TODO:change this to true when move to production
            DriverManagerDataSource ds=new DriverManagerDataSource();
//            ds.setDriverClassName("org.mariadb.jdbc.Driver");
            ds.setDriverClassName("com.mysql.jdbc.Driver");
//            ds.setUrl("jdbc:mariadb://localhost:3306/test");
//            ds.setUrl("jdbc:mysql://47.94.214.88:3306/androidconn?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=true");
            ds.setUrl("jdbc:mysql://localhost:3306/hsino?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=true");
            ds.setUsername("root");
            ds.setPassword("comp3030j");
//            ds.setPassword("");
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
