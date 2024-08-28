package com.PFE.EndOfYearProject.Controllers;

import com.PFE.EndOfYearProject.RequestResponse.UserRequest;
import com.PFE.EndOfYearProject.RequestResponse.UserUpdateRequest;
import com.PFE.EndOfYearProject.Security.JwtService;
import com.PFE.EndOfYearProject.Services.UserService;
import com.PFE.EndOfYearProject.dto.NumberDto;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.exception.InvalidPasswordException;
import com.PFE.EndOfYearProject.models.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private JwtService jwtService;
    @GetMapping("/usersList")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> Users = userService.findAllUsers();
        return ResponseEntity.ok(Users);
    }
    @GetMapping("/groupeuser")
    public ResponseEntity<List<UserDto>> getGroupeUsers() {
        List<UserDto> Users = userService.findGroupeUsers();
        return ResponseEntity.ok(Users);
    }
    @PatchMapping("/update")
    public ResponseEntity<?> updateConnectedUser(@RequestBody UserUpdateRequest updateUserRequest, Authentication authentication) {
        try {
        Integer updatedUser = userService.updateUser(authentication, updateUserRequest);
        return ResponseEntity.ok(updatedUser);
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("add-user")
    public ResponseEntity<Integer> adduser(@RequestBody UserRequest userRequest ) {
        return ResponseEntity.ok(userService.adduser(userRequest));

    }
    @PostMapping("/create-user")
    public ResponseEntity<Integer> create(@RequestBody UserRequest userRequest ) {
        return ResponseEntity.ok(userService.createUser(userRequest));

    }
    @DeleteMapping("/remove-member/{memberId}")
    public ResponseEntity<String> removeMember(@PathVariable("memberId") Integer Id) {
        userService.removeMemberFromGroup(Id);
        return ResponseEntity.ok("Member removed successfully.");
    }
    @GetMapping("/findUser/{userId}")
    public ResponseEntity<UserDto>getUser(@PathVariable("userId") Integer userId) {
        UserDto User = userService.findUser(userId);
        return ResponseEntity.ok(User);
    }



}
