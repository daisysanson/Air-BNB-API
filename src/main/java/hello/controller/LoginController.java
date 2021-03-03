package hello.controller;

import hello.dao.RoleRepository;
import hello.model.Role;
import hello.model.User;
import hello.service.RoleService;
import hello.service.SiteUserDetails;
import hello.service.UserService;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    public static final String REDIRECT= "redirect:registrationForm";
    static Logger log = Logger.getLogger(LoginController.class);
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public LoginController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

    @GetMapping("/index")
    public String getHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("loggedinuser", authentication.getName());
        model.addAttribute("title", "Home");
        return "index";
    }

    @GetMapping("/registrationForm")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("bob", roleService.getAllRoles());
        model.addAttribute("user", user);
        model.addAttribute("title", "Register an Account");


        return "registrationForm";
    }


    @PostMapping("/registrationResult")
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        List<BindingResult> listOfErrorMessages = new ArrayList<>();

        User userExists = userService.findUserByEmail(user.getEmail());
        System.out.print(user.getRoles().isEmpty());

        if (userExists != null) {
            redirectAttributes.addFlashAttribute("rejectMessage", "Sorry! That email has already been used!");
            return "redirect:registrationForm";
        }
        if (bindingResult.hasErrors()) {
            String message =  bindingResult.getFieldError().getDefaultMessage();
            redirectAttributes.addFlashAttribute("rejectMessage", message);
        log.info("Password is not valid");
        return "redirect:registrationForm";
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


    @GetMapping("/account")
    public String viewUserAccountForm(
            @AuthenticationPrincipal SiteUserDetails userDetails,
            Model model) {
        String userEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(userEmail);

        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Account Details");

        return "users/account_form";
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
