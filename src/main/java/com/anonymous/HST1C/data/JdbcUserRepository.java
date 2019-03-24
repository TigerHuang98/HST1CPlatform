package com.anonymous.HST1C.data;

import com.anonymous.HST1C.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JdbcUserRepository implements UserRepository {

    private static final String ADD_USER="INSERT INTO User (firstname,lastname,password)values(:firstname,:lastname,:password)";
    private static final String FIND_USER="SELECT id,firstname,lastname FROM User ";
    private static final String _BY_ID="WHERE id=:id";

    private static final class UserRowMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    null
            );
        }
    }

    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public JdbcUserRepository(NamedParameterJdbcOperations namedParameterJdbcOperations){
        this.namedParameterJdbcOperations=namedParameterJdbcOperations;
    }

    @Override
    public long addUser(User user) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("firstname",user.getFirstName());
        paramMap.put("lastname",user.getLastName());
        paramMap.put("password",user.getPassword());
        SqlParameterSource paramSource=new MapSqlParameterSource(paramMap);
        namedParameterJdbcOperations.update(ADD_USER,paramSource,keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public User findUser(long id) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("id",id);
        return namedParameterJdbcOperations.queryForObject(FIND_USER+_BY_ID,paramMap,new UserRowMapper());
    }



}
