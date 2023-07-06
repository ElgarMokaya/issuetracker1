package com.example.issuetracker.controller;

import com.example.issuetracker.configuration.AuthenticationManager;
import com.example.issuetracker.configuration.JwtTokenProvider;
import com.example.issuetracker.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.issuetracker.model.User;
import com.example.issuetracker.model.LoginRequest;
import com.example.issuetracker.model.ResetPasswordRequest;
import com.example.issuetracker.model.ForgotPasswordRequest;
import com.example.issuetracker.model.AuthResponse;




@RestController
@RequestMapping("/api/auth")
public class authController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);

        // Retrieve the authenticated user from the database
        User user = userRepository.findByUsername(loginRequest.getUsername());

        // Create a response object containing the JWT and user role
        AuthResponse authResponse = new AuthResponse(jwt, user.getRole());

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByUsername(forgotPasswordRequest.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Generate a password reset token and send it to the user's email
        String resetToken = generateResetToken();

        // Update the user's password reset token and token expiration date in the database
        user.setResetToken(resetToken);
        user.setTokenExpirationDate(calculateTokenExpirationDate());

        userRepository.save(user);

        // Send the reset token to the user's email (code not shown)

        return ResponseEntity.ok("Password reset token sent to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        User user = userRepository.findByResetToken(resetPasswordRequest.getResetToken());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid reset token");
        }

        if (isTokenExpired(user.getTokenExpirationDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reset token expired");
        }

        // Update the user's password
        user.setPassword(resetPasswordRequest.getPassword());

        // Clear the reset token and token expiration date
        user.setResetToken(null);
        user.setTokenExpirationDate(null);

        userRepository.save(user);

        return ResponseEntity.ok("Password reset successful");
    }

    // Helper methods for token generation and expiration checking
}
