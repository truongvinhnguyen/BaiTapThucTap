package org.example.baitapthuctap.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
//    private Integer id;
    @NotBlank(message = "Name không được để trống")
    private String name;
    @NotBlank(message = "Email không được để trống")
    private String email;
}
