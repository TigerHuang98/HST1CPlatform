package com.anonymous.HST1C.data;

import com.anonymous.HST1C.Item;
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
public class JdbcItemRepository implements ItemRepository {

    private static final String ADD_ITEM="INSERT INTO item(`itemid`,`picture`,`price`,`itemname`,`username`,`description`) VALUES (:itemid,:picture,:price,:itemname,:username,:description)";
    private static final String FIND_ITEM="SELECT `itemid`,`picture`,`price`,`itemname`,`username`,`description` FROM item ";
    private static final String _BY_ID="WHERE `itemid`=:itemid";
    private static final String _BY_USERNAME="WHERE `username`=:username";

    private static final class ItemRowMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item=new Item(
                    rs.getInt("itemid"),
                    rs.getBlob("picture"),
                    rs.getBigDecimal("price"),
                    rs.getString("itemname"),
                    rs.getString("username"),
                    rs.getString("description")
            );
            item.readBlobToBytes();
            return item;
        }
    }

    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public JdbcItemRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int addItem(Item item) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("itemid",item.getItemid());
        paramMap.put("picture",item.getPicture());
        paramMap.put("price",item.getPrice());
        paramMap.put("itemname",item.getItemname());
        paramMap.put("username",item.getUsername());
        paramMap.put("description",item.getDescription());
        SqlParameterSource paramSource=new MapSqlParameterSource(paramMap);
        namedParameterJdbcOperations.update(ADD_ITEM,paramSource,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Item findItemById(int itemid) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("itemid", itemid);
        try{
            return namedParameterJdbcOperations.queryForObject(FIND_ITEM + _BY_ID, paramMap, new JdbcItemRepository.ItemRowMapper());
        }catch(EmptyResultDataAccessException e){//Item detail not find
            return null;
        }
    }

    @Override
    public List<Item> findItemsByUsername(String username) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username",username);
        try{
            return namedParameterJdbcOperations.query(FIND_ITEM + _BY_USERNAME, paramMap, new JdbcItemRepository.ItemRowMapper());
        }catch(EmptyResultDataAccessException e){//Item detail not find
            return null;
        }
    }
}
