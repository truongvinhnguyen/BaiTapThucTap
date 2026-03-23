package org.example.baitapthuctap.service;

import lombok.RequiredArgsConstructor;
import org.example.baitapthuctap.entity.User;
import org.example.baitapthuctap.model.request.UserRequest;
import org.example.baitapthuctap.model.response.UserResponse;
import org.example.baitapthuctap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .toList();
    }

    public UserResponse create(UserRequest req) {

        if(userRepository.existsByEmail(req.getEmail())){
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());

        userRepository.save(user);

        return new UserResponse(user);
    }

    public void delete(Integer id){

        if(!userRepository.existsById(id)){
            throw new RuntimeException("User không tồn tại");
        }

        userRepository.deleteById(id);
    }
}
