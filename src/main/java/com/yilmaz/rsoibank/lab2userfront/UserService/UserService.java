package com.yilmaz.rsoibank.lab2userfront.UserService;

import com.yilmaz.rsoibank.lab2userfront.domain.User;
import com.yilmaz.rsoibank.lab2userfront.domain.security.UserRole;

import java.util.Set;


public interface UserService {

    User findByEmail(String Email);

    User findByUsername(String username);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    void save(User user);

    public User createUser(User user, Set<UserRole> userRoles);
}
