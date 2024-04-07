package com.PFE.EndOfYearProject.Controllers;

import com.PFE.EndOfYearProject.Services.UserService;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listusers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "users-list";
    }

    @GetMapping("/users/{userId}")
    public String userDetail(@PathVariable("userId") long userId,Model model){
        UserDto userDto =userService.findUseById(userId);
        model.addAttribute("user",userDto);
        return "users-detail";

    }

    @GetMapping("/users/new")
    public String CreateUserForm(Model model){
        Users user= new Users();
        model.addAttribute("user",user);
        return"users-create";
    }

    @GetMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable("userId") long userId){
        userService.delete(userId);
        return "redirect:/users";
    }

    @GetMapping("/users/search")
    public String searchUser(@RequestParam(value = "query") String query,Model model){
        List<UserDto> users = userService.searchUsers(query);
        model.addAttribute("users",users);
        return "users-list";
    }

   // @PostMapping("/users/new")
   // public String SaveUser(@Valid @ModelAttribute("user")UserDto userDto,BindingResult result,Model model ){

     //  if(result.hasErrors()){
       //    model.addAttribute("user",userDto);
         //  return "users-create";
       //} userService.saveUser(userDto);
        //return "redirect:/users";
    //}

    @GetMapping("/users/{userId}/edit")
    public String editUserForm(@PathVariable("userId") long userId,Model model){
        UserDto user= userService.findUseById(userId);
        model.addAttribute("user",user);
        return "users-edit";
    }

    @PostMapping("/users/{userId}/edit")
    public String updateUser(@PathVariable("userId") long userId,
                             @Valid @ModelAttribute("user") UserDto user , BindingResult result){
        if(result.hasErrors()){
            return "users-edit";
        }
        user.setId(userId);
        userService.updateUser(user);
        return "redirect:/users";
    }
}
