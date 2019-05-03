package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Message;
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
import java.util.List;
import java.util.Map;

@Repository
public class JdbcMessageRepository implements MessageRepository {

    private static final String ADD_MESSAGE="INSERT INTO message(`text`,`messagedate`,`ordernumber`,`issend`) VALUES (:text,:messagedate,:ordernumber,:issend)";
    private static final String FIND_MESSAGE="SELECT `messageid`,`text`,`messagedate`,`ordernumber`,`issend` FROM message ";
    private static final String _BY_ID="WHERE `messageid`=:messageid";
    private static final String _BY_ORDERNUMBER="WHERE `ordernumber`=:ordernumber";


    private static final class MessageRowMapper implements RowMapper<Message>{

        @Override
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            String isSendString=rs.getString("issend");
            boolean isSend=false;
            if(isSendString.equals("Y")){
                isSend=true;
            }
            if(isSendString.equals("N")){
                isSend=false;
            }
            return new Message(
                    rs.getInt("messageid"),
                    rs.getString("text"),
                    rs.getTimestamp("messagedate"),
                    rs.getInt("ordernumber"),
                    isSend
            );
        }
    }

    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public JdbcMessageRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int addMessage(Message message) {
        String isSendString="";
        if(message.isSend()){
            isSendString="Y";
        }else {
            isSendString="N";
        }
        KeyHolder keyHolder=new GeneratedKeyHolder();
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("text",message.getText());
        paramMap.put("messagedate",message.getMessagedate());
        paramMap.put("ordernumber",message.getOrdernumber());
        paramMap.put("issend",isSendString);
        SqlParameterSource paramSource=new MapSqlParameterSource(paramMap);
        namedParameterJdbcOperations.update(ADD_MESSAGE,paramSource,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Message findMessageById(int messageid) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("messageid",messageid);
        try{
            return namedParameterJdbcOperations.queryForObject(FIND_MESSAGE + _BY_ID, paramMap, new MessageRowMapper());
        }catch(EmptyResultDataAccessException e){//Message detail not find
            return null;
        }
    }

    @Override
    public List<Message> findMessagesByOrdernumber(int ordernumber) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ordernumber",ordernumber);
        try{
            return namedParameterJdbcOperations.query(FIND_MESSAGE + _BY_ORDERNUMBER, paramMap, new MessageRowMapper());
        }catch(EmptyResultDataAccessException e){//Message detail not find
            return null;
        }
    }
}
