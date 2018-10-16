package com.yilmaz.rsoibank.lab2userfront.UserService.UserServiceImpl;


import com.yilmaz.rsoibank.lab2userfront.Dao.UserDao;
import com.yilmaz.rsoibank.lab2userfront.UserService.UserService;
import com.yilmaz.rsoibank.lab2userfront.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public boolean checkUserExists(String userName, String email) {
        return checkUserNameExists(userName) || checkEmailExists(userName);
    }

    public boolean checkUserNameExists(String userName) {
        return null != userDao.findByUserName(userName);
    }

    public boolean checkEmailExists(String email) {
        return null != userDao.findByEmail(email);
    }
}
