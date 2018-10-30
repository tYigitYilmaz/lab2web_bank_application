package com.yilmaz.rsoibank.lab2userfront.UserService.UserServiceImpl;


import com.yilmaz.rsoibank.lab2userfront.UserService.AccountService;
import com.yilmaz.rsoibank.lab2userfront.dao.RoleDao;
import com.yilmaz.rsoibank.lab2userfront.dao.UserDao;
import com.yilmaz.rsoibank.lab2userfront.UserService.UserService;
import com.yilmaz.rsoibank.lab2userfront.domain.User;
import com.yilmaz.rsoibank.lab2userfront.domain.security.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder passwordEncoder;
    private AccountService accountService;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    public UserDao getUserDaoe() {
        return userDao;
    }
    @Autowired
    public void setRoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }
    public RoleDao getRoleDao() {
        return roleDao;
    }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }
    public AccountService getAccountService() {
        return accountService;
    }

    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    public User createUser(User user, Set<UserRole>userRoles){
        User localUser = userDao.findByUsername(user.getUsername());
        if (localUser != null){
            LOG.info("User with username{} already exist.Nothing will be done.", user.getUsername());
        }else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRoles){
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userDao.save(user);
        }
        return localUser;
    }

    public boolean checkUserExists(String username, String email) {
        return checkUsernameExists(username) || checkEmailExists(username);
    }

    public boolean checkUsernameExists(String username) {
        return null != userDao.findByUsername(username);
    }

    public boolean checkEmailExists(String email) {
        return null != userDao.findByEmail(email);
    }
}
