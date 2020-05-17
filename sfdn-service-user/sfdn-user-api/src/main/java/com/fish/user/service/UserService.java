package com.fish.user.service;

import com.fish.user.model.User;

/**
 * @author hh
 */
public interface UserService {
    /**
     * 数据业务层getBean
     * @return
     */
    User getUser(int id);
}
