package com.anonymous.HST1C.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

//@Configuration
//@EnableWebMvcSecurity
public class SecurityConfig //extends WebSecurityConfigurerAdapter*/
{
//    @Autowired
//    DataSource dataSource;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//        .jdbcAuthentication()
//        .dataSource(dataSource)
//        .usersByUsernameQuery(
//                "select id,password,true from User where id=?"
//        )
//        .authoritiesByUsernameQuery(
//                "select id, 'ROLE_USER' from User where id=?"
//        )
//        .passwordEncoder(new BCryptPasswordEncoder());
//    }
}
