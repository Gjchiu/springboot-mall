package com.gjchiu.springbootmall.dao;

import com.gjchiu.springbootmall.dto.UserRegisterRequest;
import com.gjchiu.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
}
