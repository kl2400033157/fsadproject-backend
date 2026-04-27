package com.webinarhub.platform.controller;

import com.webinarhub.platform.dto.LoginRequest;
import com.webinarhub.platform.dto.LoginResponse;
import com.webinarhub.platform.dto.RegisterRequest;
import com.webinarhub.platform.dto.UserDto;
import com.webinarhub.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication REST Controller.
 * Demonstrates:
 * - @RestController (combines @Controller + @ResponseBody)
 * - @RequestMapping for base URL
 * - @PostMapping for HTTP POST method mapping
 * - @RequestBody for deserializing JSON request body
 * - ResponseEntity for HTTP response with status code
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST /api/auth/request-otp
     * Request an OTP for registration
     */
    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody java.util.Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        userService.generateAndSendRegistrationOtp(email);
        return ResponseEntity.ok().body("OTP sent successfully to " + email);
    }

    /**
     * POST /api/auth/register
     * Register a new user and return a LoginResponse with token (same shape as login)
     */
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        UserDto user = userService.registerUser(request);
        LoginResponse response = new LoginResponse("Registration successful", user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * POST /api/auth/login
     * Login user - @RequestBody receives credentials
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}
