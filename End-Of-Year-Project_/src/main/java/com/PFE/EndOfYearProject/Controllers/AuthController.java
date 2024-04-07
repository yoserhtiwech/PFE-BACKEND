package com.PFE.EndOfYearProject.Controllers;

import com.PFE.EndOfYearProject.Services.UserService;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Users;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserDto user= new UserDto();
        model.addAttribute("user",user);
        return "register";

    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")UserDto user,
                           BindingResult result, Model model) {
        Users existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
           // result.rejectValue("email","there is already a user with this email/username");
            return "redirect:/register?fail";
        }
        Users existingUserLastname = userService.findByLastName(user.getUsername());
        if(existingUserLastname != null && existingUserLastname.getUsername() != null && !existingUserLastname.getUsername().isEmpty()) {
            //result.rejectValue("email","there is already a user with this email/username");
            return "redirect:/register?fail";
        }
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/users?success";
    }
}
