package org.example.baitapthuctap.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.baitapthuctap.enums.TaskStatus;

import java.time.LocalDate;

@Getter
@Setter
public class TaskRequest {
//    private Integer id;

    @NotBlank(message = "Title không được để trống")
    @Size(min = 3, max = 100, message = "Title phải từ 3-100 ký tự")
    private String title;

    @NotBlank(message = "Description không được để trống")
    private String description;

    @NotNull(message = "ProjectId không được để trống")
    private Integer projectId;

    private TaskStatus status;

    private LocalDate deadline;
}
