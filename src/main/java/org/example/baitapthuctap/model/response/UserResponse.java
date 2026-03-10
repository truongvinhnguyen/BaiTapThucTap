package org.example.baitapthuctap.model.response;

import lombok.Getter;
import lombok.Setter;
import org.example.baitapthuctap.entity.User;

@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String name;
    private String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
