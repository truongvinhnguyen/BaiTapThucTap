package org.example.baitapthuctap.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
//    private Integer id;

    @NotBlank(message = "Title không được để trống")
    private String title;

    @NotBlank(message = "Description không được để trống")
    private String description;

    @NotNull(message = "Status không được null")
    private Integer status;
}
