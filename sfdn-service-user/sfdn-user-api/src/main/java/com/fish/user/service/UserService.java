package com.fish.user.service;

import com.fish.core.model.ResponseEntity;
import com.fish.user.model.User;

import java.util.List;

/**
 * @author hh
 */
public interface UserService {
    int insert(User user);
    int update(User user);
    User findById(Integer id);
    User findByCondition(User user);
    List<User> findListByCondition(User user);

    ResponseEntity isExist(User user);
}
