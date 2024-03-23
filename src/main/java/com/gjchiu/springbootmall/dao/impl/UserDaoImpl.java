package com.gjchiu.springbootmall.dao.impl;

import com.gjchiu.springbootmall.dao.UserDao;
import com.gjchiu.springbootmall.dto.UserRegisterRequest;
import com.gjchiu.springbootmall.model.User;
import com.gjchiu.springbootmall.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        final String sql = "insert into user " +
                "(email, password, created_date, last_modified_date) " +
                "values (:email, :password, :createdDate, :lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());

        Date today = new Date();
        map.put("createdDate", today);
        map.put("lastModifiedDate", today);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map) , keyHolder);
        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public User getUserById(Integer userId) {
        final String sql = "select user_id, email, password, created_date, last_modified_date " +
                "from user where user_id = :userId";
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map,new UserRowMapper());
        if(!userList.isEmpty()){
            return userList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        final String sql = "select user_id, email, password, created_date, last_modified_date " +
                "from user where email = :email";
        Map<String,Object> map = new HashMap<>();
        map.put("email", email);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map,new UserRowMapper());
        if(!userList.isEmpty()){
            return userList.get(0);
        }else {
            return null;
        }
    }
}
