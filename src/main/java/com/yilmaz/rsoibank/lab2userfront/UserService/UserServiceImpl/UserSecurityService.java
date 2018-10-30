package com.yilmaz.rsoibank.lab2userfront.UserService.UserServiceImpl;

import com.yilmaz.rsoibank.lab2userfront.dao.UserDao;
import com.yilmaz.rsoibank.lab2userfront.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    /** the application Logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null){
            LOG.warn("Username{} not found", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return user;
    }
}
