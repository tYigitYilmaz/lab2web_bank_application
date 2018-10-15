package com.yilmaz.rsoibank.lab2userfront.Controller;


import com.yilmaz.rsoibank.lab2userfront.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    /*private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }
*/
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
   /* @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String  signupPost(@ModelAttribute("user")User user, Model model){
        if (userService.checkUserExists(user.getUserName(),user.getEmail())){
            if(userService.checkEmailExists(user.getEmail())){
                model.addAttribute("emailExits",true);
            }
            if(userService.checkUserNameExists(user.getUserName())){
                model.addAttribute("usernameExists",true);
            }
            return "signup";
        }else{
            userService.save(user);

            return "redirect:/";
        }
    }*/
}
