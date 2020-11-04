package hello.controller;

import hello.dao.UserRepository;
import hello.exceptions.BadRequestException;
import hello.model.User;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }


    @GetMapping("/registrationForm")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("title", "Register an Account");


        return "registrationForm";
    }


    @PostMapping("/registrationResult")
    public String createNewUser(@Valid User user, Model model, RedirectAttributes redirectAttributes) {

        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null){
            redirectAttributes.addFlashAttribute("rejectMessage", "Sorry! That email has already been used!");
        return "redirect:registrationForm" ;
        }
        userService.saveNewUser(user);
        model.addAttribute("successMessage", "User has been registered successfully!");
        model.addAttribute("title", "Registration Success!");
        model.addAttribute("user", user);

        return "registrationResult";
    }





    @GetMapping("/error")
    public String redirectToError(Model model, Error error) {
        model.addAttribute("error", error);
        model.addAttribute("title", "Error");

        return "error";

    }


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("currentUser", user);
        model.addAttribute("userName", "Welcome " + user.getUserName());
        model.addAttribute("title", "Your Account!");
        model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");

        return "dashboard";
    }


}
