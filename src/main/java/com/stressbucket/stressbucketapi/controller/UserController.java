package com.stressbucket.stressbucketapi.controller;

import com.stressbucket.stressbucketapi.Constants;
import com.stressbucket.stressbucketapi.model.User;
import com.stressbucket.stressbucketapi.service.UserService;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, Object> userMap) {
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        User user = userService.register(username, password);
        return new ResponseEntity<>(generateJWTToken(user),  HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, Object> userMap) {
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        User user = userService.validate(username, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        Dotenv dotenv = Dotenv.load();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, dotenv.get("JWT_SECRET_KEY"))
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("username", user.getUsername())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}

