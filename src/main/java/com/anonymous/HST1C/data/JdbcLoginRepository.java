package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Login;
import com.anonymous.HST1C.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcLoginRepository implements LoginRepository {

    private static final String ADD_LOGIN="INSERT INTO login(`username`,`password`,`uid`) VALUES (:username,:password,'customer')";
    private static final String FIND_LOGIN="SELECT `username`,`password`,`uid`,`userid` FROM login ";
    private static final String _BY_ID="WHERE `userid`=:userid";
    private static final String _BY_USERNAME="WHERE `username`=:username";

    private static final class LoginRowMapper implements RowMapper<Login>{

        @Override
        public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
            String roleString=rs.getString("uid");
            Role role=null;
            if(roleString.equals("customer")){
                role=Role.CUSTOMER;
            }
            if(roleString.equals("stuff")){
                role=Role.STUFF;
            }

            return new Login(
                    rs.getString("username"),
                    rs.getString("password"),
                    role,
                    rs.getInt("userid")
            );
        }
    }

    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public JdbcLoginRepository(NamedParameterJdbcOperations namedParameterJdbcOperations){
        this.namedParameterJdbcOperations=namedParameterJdbcOperations;
    }

    @Override
    public int addCustomerLogin(Login login) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("username",login.getUsername());
        paramMap.put("password",login.getPassword());
        SqlParameterSource paramSource=new MapSqlParameterSource(paramMap);
        namedParameterJdbcOperations.update(ADD_LOGIN,paramSource,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Login findLoginById(int userid) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", userid);
        try{
            return namedParameterJdbcOperations.queryForObject(FIND_LOGIN + _BY_ID, paramMap, new LoginRowMapper());
        }catch(EmptyResultDataAccessException e){//Login detail not find
            return null;
        }
    }

    @Override
    public Login findLoginByUsername(String username) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("username",username);
        try{
            return namedParameterJdbcOperations.queryForObject(FIND_LOGIN+_BY_USERNAME,paramMap,new LoginRowMapper());
        }catch(EmptyResultDataAccessException e){//Login detail not find
            return null;
        }
    }
}
