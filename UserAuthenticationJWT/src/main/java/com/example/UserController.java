package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login/{uid}/{email}")
    public ResponseEntity<?> loginUser(@PathVariable Integer uid, @PathVariable String email) {
        Map<String, String> response = userService.loginUser(uid, email);
        if (response.containsKey("error")) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user")
    public String getUser() {
        return "Hello Everyone I am token protected";
    }

}
