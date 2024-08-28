package com.PFE.EndOfYearProject.Controllers;
import com.PFE.EndOfYearProject.Services.UserService;
import com.PFE.EndOfYearProject.dto.AuthenticationDto;
import com.PFE.EndOfYearProject.dto.AuthenticationResponse;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.CompleteRegistrationRequest;
import com.PFE.EndOfYearProject.models.RegistrationRequest;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationDto request) {

     return ResponseEntity.ok(userService.login(request));

    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        userService.register(request);
        return ResponseEntity.accepted().build();
    }
    @PostMapping("/complete-registration")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> completeRegistration(@RequestBody @Valid CompleteRegistrationRequest request) {
        userService.completeRegistration(request);
        return ResponseEntity.ok("Registration completed successfully");
    }
    @GetMapping("/activate-account")
    public void confirm(@RequestParam String token) throws MessagingException {
        userService.activateAccount(token);
    }
    @GetMapping("/user-details")
    public ResponseEntity<UserDto> getUserDetails(@RequestParam String token) {
        UserDto userDto = userService.getUserDetailsForToken(token);
        return ResponseEntity.ok(userDto);
    }


}
