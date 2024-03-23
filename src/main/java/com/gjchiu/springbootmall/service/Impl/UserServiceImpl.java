package com.gjchiu.springbootmall.service.Impl;

import com.gjchiu.springbootmall.dao.UserDao;
import com.gjchiu.springbootmall.dto.UserRequest;
import com.gjchiu.springbootmall.model.User;
import com.gjchiu.springbootmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public Integer register(UserRequest userRequest) {
        return userDao.createUser(userRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
