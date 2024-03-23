package com.gjchiu.springbootmall.service;

import com.gjchiu.springbootmall.dto.UserLoginRequest;
import com.gjchiu.springbootmall.dto.UserRegisterRequest;
import com.gjchiu.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User login(UserLoginRequest userLoginRequest);
}
