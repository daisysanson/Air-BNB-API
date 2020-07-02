package hello.controller;


import hello.exceptions.BadRequestException;
import hello.model.User;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;


    @RequestMapping("/registrationForm")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "registrationForm";

    }



    @RequestMapping("/registrationResult")
    public String createNewUser(@Valid User user, Model model) {
        try {

            User userExists = userService.findUserByEmail(user.getEmail());

            userService.saveNewUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
        } catch (BadRequestException e){
            return "badRequest";
        }

        return "registrationResult";
    }


}