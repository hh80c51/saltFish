package dao;

import model.User;

/**
 * @ClassName UserMapper
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/15
 * @Version V1.0
 **/
public interface UserDao {
    void insertUser(User user);
    User getUser(Integer id);
}
