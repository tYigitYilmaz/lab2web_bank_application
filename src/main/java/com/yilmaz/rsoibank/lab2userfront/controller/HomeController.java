package com.yilmaz.rsoibank.lab2userfront.controller;


import com.yilmaz.rsoibank.lab2userfront.UserService.UserService;
import com.yilmaz.rsoibank.lab2userfront.dao.RoleDao;
import com.yilmaz.rsoibank.lab2userfront.domain.PrimaryAccount;
import com.yilmaz.rsoibank.lab2userfront.domain.SavingsAccount;
import com.yilmaz.rsoibank.lab2userfront.domain.User;
import com.yilmaz.rsoibank.lab2userfront.domain.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
public class HomeController {

    private UserService userService;
    private RoleDao roleDao;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }
    public RoleDao getroleDao() {
        return roleDao;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model){
        User user = new User();

        model.addAttribute("user",user);
        return "signup";
    }
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String  signupPost(@ModelAttribute("user")User user, Model model){
        if (userService.checkUserExists(user.getUsername(),user.getEmail())){
            if(userService.checkEmailExists(user.getEmail())){
                model.addAttribute("emailExits",true);
            }
            if(userService.checkUsernameExists(user.getUsername())){
                model.addAttribute("usernameExists",true);
            }
            return "signup";
        }else{

            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);

            return "redirect:/";
        }
    }

   @RequestMapping("/userFront")
    public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("savingsAccount", savingsAccount);

        return "userFront";
    }
}
