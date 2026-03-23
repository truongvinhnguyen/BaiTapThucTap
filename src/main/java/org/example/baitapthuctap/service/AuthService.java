package org.example.baitapthuctap.service;

import org.example.baitapthuctap.entity.Role;
import org.example.baitapthuctap.entity.User;
import org.example.baitapthuctap.model.request.LoginRequest;
import org.example.baitapthuctap.model.request.RegisterRequest;
import org.example.baitapthuctap.model.response.AuthResponse;
import org.example.baitapthuctap.repository.RoleRepository;
import org.example.baitapthuctap.repository.UserRepository;
import org.example.baitapthuctap.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(RegisterRequest request){

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository.findByName("USER").orElseThrow();

        user.setRoles(Set.of(role));

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request){

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Sai mật khẩu");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}