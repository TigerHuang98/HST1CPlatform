package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Order;
import com.anonymous.HST1C.Status;
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
public class JdbcOrderRepository implements OrderRepository {


    private static final String ADD_ORDER="INSERT INTO `ordertable`(`username`,`itemid`,`status`,`lostdate`) VALUES (:username,:itemid,:status,:lostdate)";
    private static final String FIND_ORDER="SELECT `ordernumber`,`orderdate`,`username`,`itemid`,`status`,`lostdate` FROM `ordertable` ";
    private static final String UPDATE_ORDER_STATUS="UPDATE `ordertable` SET status=:status ";
    private static final String _BY_ID="WHERE `ordernumber`=:ordernumber";
    private static final String _BY_ITEMID="WHERE `itemid`=:itemid";
    private static final String _BY_USERNAME="WHERE `username`=:username";
    private static final String _BY_STATUS="WHERE `status`=:status";

    private static final class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            String statusString=rs.getString("status");
            Status status=null;
            if(statusString.equals("processing")){
                status=Status.PROCESSING;
            }
            if(statusString.equals("denying")){
                status=Status.DENYING;
            }
            if(statusString.equals("approving")){
                status=Status.APPROVING;
            }
            return new Order(
                    rs.getInt("ordernumber"),
                    rs.getTimestamp("orderdate"),
                    rs.getString("username"),
                    rs.getInt("itemid"),
                    status,
                    rs.getDate("lostdate")
            );
        }
    }

    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public JdbcOrderRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int addOrder(Order order) {
        String statusString="";
        if(order.getStatus().equals(Status.PROCESSING)){
            statusString="processing";
        }
        if(order.getStatus().equals(Status.DENYING)){
            statusString="denying";
        }
        if(order.getStatus().equals(Status.APPROVING)){
            statusString="approving";
        }
        KeyHolder keyHolder=new GeneratedKeyHolder();
        Map<String,Object> paramMap=new HashMap<>();
//        paramMap.put("orderdate",order.getOrderdate());
        paramMap.put("username",order.getUsername());
        paramMap.put("itemid",order.getItemid());
        paramMap.put("status",statusString);
        paramMap.put("lostdate",order.getLostdate());
        SqlParameterSource paramSource=new MapSqlParameterSource(paramMap);
        namedParameterJdbcOperations.update(ADD_ORDER,paramSource,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Order findOrderByOrdernumber(int ordernumber) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ordernumber",ordernumber);
        try{
            return namedParameterJdbcOperations.queryForObject(FIND_ORDER + _BY_ID, paramMap, new JdbcOrderRepository.OrderRowMapper());
        }catch(EmptyResultDataAccessException e){//Order detail not find
            return null;
        }
    }

    @Override
    public List<Order> findOrdersByItemid(int itemid) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("itemid",itemid);
        try{
            return namedParameterJdbcOperations.query(FIND_ORDER + _BY_ITEMID, paramMap, new JdbcOrderRepository.OrderRowMapper());
        }catch(EmptyResultDataAccessException e){//Order detail not find
            return null;
        }
    }

    @Override
    public List<Order> findOrdersByUsername(String username) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username",username);
        try{
            return namedParameterJdbcOperations.query(FIND_ORDER + _BY_USERNAME, paramMap, new JdbcOrderRepository.OrderRowMapper());
        }catch(EmptyResultDataAccessException e){//Order detail not find
            return null;
        }
    }

    @Override
    public List<Order> findOrdersByStatus(Status status) {
        Map<String, Object> paramMap = new HashMap<>();
        convertStatusToParam(status, paramMap);
        try{
            return namedParameterJdbcOperations.query(FIND_ORDER + _BY_STATUS, paramMap, new JdbcOrderRepository.OrderRowMapper());
        }catch(EmptyResultDataAccessException e){//Order detail not find
            return null;
        }
    }

    @Override
    public void updateOrderStatusByOrdernumber(int ordernumber,Status status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ordernumber",ordernumber);
        convertStatusToParam(status, paramMap);
        namedParameterJdbcOperations.update(UPDATE_ORDER_STATUS+_BY_ID,paramMap);
    }

    private void convertStatusToParam(Status status, Map<String, Object> paramMap) {
        String statusString="";
        switch (status){
            case PROCESSING:
                statusString="processing";
                break;
            case DENYING:
                statusString="denying";
                break;
            case APPROVING:
                statusString="approving";
                break;
        }
        paramMap.put("status",statusString);
    }
}
