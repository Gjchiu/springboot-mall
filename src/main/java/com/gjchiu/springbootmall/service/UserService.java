package com.gjchiu.springbootmall.service;

import com.gjchiu.springbootmall.dto.UserLoginRequest;
import com.gjchiu.springbootmall.dto.UserRequest;
import com.gjchiu.springbootmall.model.User;

public interface UserService {
    Integer register(UserRequest userRequest);
    User getUserById(Integer userId);
    User login(UserLoginRequest userLoginRequest);
}
