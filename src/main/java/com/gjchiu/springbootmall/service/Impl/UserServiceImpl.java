package com.gjchiu.springbootmall.service.Impl;

import com.gjchiu.springbootmall.dao.UserDao;
import com.gjchiu.springbootmall.dto.UserRequest;
import com.gjchiu.springbootmall.model.User;
import com.gjchiu.springbootmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    @Override
    public Integer register(UserRequest userRequest) {
        User user = userDao.getUserByEmail(userRequest.getEmail());

        if(user != null){
            log.warn("該email {} 已被註冊",userRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return userDao.createUser(userRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
