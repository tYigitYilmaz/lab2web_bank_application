package com.yilmaz.rsoibank.lab2userfront.Dao;



import org.springframework.data.repository.CrudRepository;
import com.yilmaz.rsoibank.lab2userfront.domain.User;

public interface UserDao extends CrudRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
