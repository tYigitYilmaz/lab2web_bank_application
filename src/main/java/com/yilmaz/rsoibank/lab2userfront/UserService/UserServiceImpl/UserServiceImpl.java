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

    public void save(User user){
        userDao.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }
    public boolean checkUserExists(String userName, String email){
        if (checkUserNameExists(userName) || checkEmailExists(userName)){
            return true;
        }else {
            return false;
        }
    }
    public boolean checkUserNameExists(String userName){
        if(null != userDao.findByUserName(userName)){
            return true;
        }
        return false;
    }
    public boolean checkEmailExists(String email){
        if(null != userDao.findByEmail(email)){
            return true;
        }
        return false;
    }
}
