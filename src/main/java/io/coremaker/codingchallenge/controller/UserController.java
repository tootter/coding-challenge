package io.coremaker.codingchallenge.controller;

import io.coremaker.codingchallenge.payload.SignInRequest;
import io.coremaker.codingchallenge.payload.SignupRequest;
import io.coremaker.codingchallenge.payload.UserDetails;
import io.coremaker.codingchallenge.repo.User;
import io.coremaker.codingchallenge.repo.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepoService userRepoService;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody SignupRequest signUpRequest) {

        if (userRepoService.emailExists(signUpRequest.getEmail()))
            return ResponseEntity
                    .badRequest()
                    .body("Email already registered.");

        userRepoService.registerNewUser(signUpRequest);

        return ResponseEntity.ok("Registration successful!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInRequest signInRequest) {

        if (!userRepoService.emailExists(signInRequest.getEmail()))
            return ResponseEntity
                    .badRequest()
                    .body("Invalid email.");

        String authToken = userRepoService.login(signInRequest);
        if (authToken == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password.");

        return ResponseEntity.ok(authToken);
    }

    @GetMapping("/details")
    public ResponseEntity<?> getUserDetails(@RequestHeader String authToken) {

        User user = userRepoService.getUserDetails(authToken);
        if (user == null)
            return ResponseEntity
                    .badRequest()
                    .body("Invalid auth token");

        return ResponseEntity.ok(new UserDetails(user.getName(), user.getEmail()));
    }
}
