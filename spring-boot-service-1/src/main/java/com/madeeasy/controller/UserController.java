package com.madeeasy.controller;


import com.madeeasy.dto.UserRequest;
import com.madeeasy.dto.UserResponse;
import com.madeeasy.service.JwtUtils;
import com.madeeasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to madeeasy.com from demo project";
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> getAuthenticated(@RequestBody UserRequest userRequest) {
        String token = null;
        boolean flag = userService.validateUserName(userRequest.getName());
        if (flag) {
            token = jwtUtils.generateToken(userRequest.getName());
            System.out.println(token);
        }
        return ResponseEntity.ok(new UserResponse(token));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
        userRequest.setName(userRequest.getName().toLowerCase());
        boolean flag = userService.saveUser(userRequest.getName(), userRequest.getPassword());
        if (flag) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}