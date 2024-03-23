package com.gjchiu.springbootmall.dao;

import com.gjchiu.springbootmall.dto.UserRequest;
import com.gjchiu.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRequest userRequest);
    User getUserById(Integer userId);
}
