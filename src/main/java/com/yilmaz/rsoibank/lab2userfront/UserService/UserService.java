package com.yilmaz.rsoibank.lab2userfront.UserService;

import com.yilmaz.rsoibank.lab2userfront.domain.User;


public interface UserService {

    User findByEmail(String Email);

    User findByUserName(String userName);

    boolean checkUserExists(String userName, String email);

    boolean checkUserNameExists(String userName);

    boolean checkEmailExists(String email);

    void save(User user);
}
