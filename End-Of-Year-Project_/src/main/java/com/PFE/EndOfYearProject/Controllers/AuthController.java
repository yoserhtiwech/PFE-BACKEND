package com.PFE.EndOfYearProject.Controllers;

import com.PFE.EndOfYearProject.Services.UserService;
import com.PFE.EndOfYearProject.dto.AuthenticationDto;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Users;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Auth")

public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid AuthenticationDto request) {

     return  ResponseEntity.ok(userService.login(request));
    }


    /*@PostMapping("/register/save")
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
    }*/
}
