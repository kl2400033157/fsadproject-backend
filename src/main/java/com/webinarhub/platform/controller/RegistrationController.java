package com.webinarhub.platform.controller;

import com.webinarhub.platform.dto.RegistrationDto;
import com.webinarhub.platform.exception.BadRequestException;
import com.webinarhub.platform.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Registration REST Controller.
 * Handles user registration for webinars.
 */
@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    private String extractUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            try {
                String id = token.substring(7);
                System.out.println("🔍 [Registration] Extracted userId from token: " + id);
                return id;
            } catch (NumberFormatException e) {
                throw new BadRequestException("Invalid token format");
            }
        }
        throw new BadRequestException("Missing or invalid Authorization header");
    }

    /**
     * POST /api/registrations - Register for a webinar
     */
    @PostMapping
    public ResponseEntity<RegistrationDto> registerForWebinar(@RequestHeader(value = "Authorization", required = false) String token, @RequestParam("webinarId") String webinarId) {
        String userId = extractUserIdFromToken(token);
        RegistrationDto registration = registrationService.registerForWebinar(userId, webinarId);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

    /**
     * GET /api/registrations - Admin query all registrations securely
     */
    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations() {
        try {
            List<RegistrationDto> allRegistrations = registrationService.getAllRegistrations();
            if (allRegistrations == null) {
                return ResponseEntity.ok(java.util.Collections.emptyList());
            }
            return ResponseEntity.ok(allRegistrations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(java.util.Collections.emptyList());
        }
    }

    /**
     * GET /api/registrations/user/me - Get current user's registrations
     */
    @GetMapping("/user/me")
    public ResponseEntity<List<RegistrationDto>> getMyRegistrations(@RequestHeader(value = "Authorization", required = false) String token) {
        String userId = extractUserIdFromToken(token);
        List<RegistrationDto> registrations = registrationService.getRegistrationsByUserId(userId);
        return ResponseEntity.ok(registrations);
    }

    /**
     * GET /api/registrations/user/{userId} - Get registrations by user ID
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RegistrationDto>> getRegistrationsByUserId(@PathVariable("userId") String userId) {
        List<RegistrationDto> registrations = registrationService.getRegistrationsByUserId(userId);
        return ResponseEntity.ok(registrations);
    }

    /**
     * GET /api/registrations/webinar/{webinarId} - Get webinar registrations
     */
    @GetMapping("/webinar/{webinarId}")
    public ResponseEntity<List<RegistrationDto>> getWebinarRegistrations(@PathVariable("webinarId") String webinarId) {
        List<RegistrationDto> registrations = registrationService.getRegistrationsByWebinarId(webinarId);
        return ResponseEntity.ok(registrations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> cancelRegistration(@PathVariable("id") String id) {
        registrationService.cancelRegistration(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration cancelled successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/registrations/cancel/{webinarId} - Cancel registration by webinar ID
     */
    @PostMapping("/cancel/{webinarId}")
    public ResponseEntity<Map<String, String>> cancelByWebinarId(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("webinarId") String webinarId) {
        String userId = extractUserIdFromToken(token);
        registrationService.cancelByUserAndWebinar(userId, webinarId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration cancelled successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * PUT /api/registrations/{id}/attend - Mark attendance
     */
    @PutMapping("/{id}/attend")
    public ResponseEntity<RegistrationDto> markAttendance(@PathVariable("id") String id) {
        RegistrationDto registration = registrationService.markAttendance(id);
        return ResponseEntity.ok(registration);
    }

    @GetMapping("/check/{webinarId}")
    public ResponseEntity<Map<String, Object>> checkRegistration(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("webinarId") String webinarId) {
        Map<String, Object> response = new HashMap<>();
        
        if (token == null || !token.startsWith("Bearer ")) {
            response.put("registered", false);
            return ResponseEntity.ok(response);
        }

        try {
            String userId = extractUserIdFromToken(token);
            Optional<RegistrationDto> registration = registrationService.getRegistrationByUserAndWebinar(userId, webinarId);
            
            response.put("registered", registration.isPresent());
            registration.ifPresent(reg -> response.put("id", reg.getId()));
        } catch (Exception e) {
            response.put("registered", false);
            response.put("error", "Session expired or invalid");
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/registrations/count/{webinarId} - Count registrations for webinar
     */
    @GetMapping("/count/{webinarId}")
    public ResponseEntity<Map<String, String>> countRegistrations(@PathVariable("webinarId") String webinarId) {
        Long count = registrationService.countByWebinarId(webinarId);
        Map<String, String> response = new HashMap<>();
        response.put("count", String.valueOf(count));
        return ResponseEntity.ok(response);
    }
}
