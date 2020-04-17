package dao;

import bean.User;

/**
 * @ClassName UserMapper
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/15
 * @Version V1.0
 **/
public interface UserMapper {
    void insertUser(User user);
    User getUser(Integer id);
}
