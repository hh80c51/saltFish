package service.impl;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author hh
 * @Date 2020/5/9
 * @Version V1.0
 **/
public class UserServiceImpl {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(int id){
        return userDao.getUser(id);
    }
}
