package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Userinfo;
import com.anonymous.HST1C.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcUserinfoRepository implements UserinfoRepository {

    private static final String ADD_USERINFO="INSERT INTO userinfo(`username`,`gender`,`phonenumber`,`emailaddress`,`birthdate`,`icon`) values(:username,:gender,:phonenumber,:emailaddress,:birthdate,:icon)";
    private static final String FIND_USERINFO="SELECT `username`,`gender`,`phonenumber`,`emailaddress`,`birthdate`,`icon` FROM userinfo ";
    private static final String _BY_USERNAME="WHERE `username`=:username";

    private static final class UserinfoRowMapper implements RowMapper<Userinfo> {

        @Override
        public Userinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            String genderString=rs.getString("gender");
            Gender gender=null;
            if(genderString.equals("M")){
                gender=Gender.MALE;
            }
            if(genderString.equals("F")){
                gender=Gender.FEMALE;
            }

            Userinfo userinfo= new Userinfo(
                    rs.getString("username"),
                    gender,
                    rs.getString("phonenumber"),
                    rs.getString("emailaddress"),
                    rs.getDate("birthdate"),
                    rs.getBlob("icon")
            );
            userinfo.readBlobToBytes();
            return userinfo;
        }
    }

    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public JdbcUserinfoRepository(NamedParameterJdbcOperations namedParameterJdbcOperations){
        this.namedParameterJdbcOperations=namedParameterJdbcOperations;
    }

    @Override
    public Userinfo addUserinfo(Userinfo userinfo) {
        String genderString="";
        if(userinfo.getGender().equals(Gender.MALE)){
            genderString="M";
        }
        if(userinfo.getGender().equals(Gender.FEMALE)){
            genderString="F";
        }
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("username",userinfo.getUsername());
        paramMap.put("gender",genderString);
        paramMap.put("phonenumber",userinfo.getPhonenumber());
        paramMap.put("emailaddress",userinfo.getEmailaddress());
        paramMap.put("birthdate",userinfo.getBirthdate());
        paramMap.put("icon",userinfo.getIcon());
        namedParameterJdbcOperations.update(ADD_USERINFO,paramMap);
        return userinfo;
    }

    @Override
    public Userinfo findUserinfo(String username) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("username",username);
        try{
            return namedParameterJdbcOperations.queryForObject(FIND_USERINFO+_BY_USERNAME,paramMap,new UserinfoRowMapper());
        }catch(EmptyResultDataAccessException e){//Userinfo detail not find
            return null;
        }
    }
}
