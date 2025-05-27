package com.example.demooo.controller;

import com.example.demooo.entity.User;
import com.example.demooo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // utile si tu fais des appels depuis une app mobile ou frontend React/Flutter
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * API pour l'inscription
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email déjà utilisé.");
        }

        userService.registerUser(user);
        return ResponseEntity.ok("Inscription réussie.");
    }

    /**
     * API pour la connexion
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        Optional<User> user = userService.authenticate(loginData.getEmail(), loginData.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // tu peux aussi retourner un token ici si tu veux sécuriser l'API
        } else {
            return ResponseEntity.status(401).body("Identifiants invalides.");
        }
    }
}
