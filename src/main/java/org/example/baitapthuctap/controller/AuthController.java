package org.example.baitapthuctap.controller;

import org.example.baitapthuctap.security.JwtUtil;
import org.example.baitapthuctap.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String,String> req){

        String username = req.get("username");
        String password = req.get("password");

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new RuntimeException("Sai mật khẩu");
        }

        String token = jwtUtil.generateToken(username);

        Map<String,String> res = new HashMap<>();
        res.put("token",token);

        return res;
    }
}