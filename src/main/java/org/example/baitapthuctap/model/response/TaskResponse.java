package org.example.baitapthuctap.model.response;

import lombok.Getter;
import lombok.Setter;
import org.example.baitapthuctap.entity.Task;
import org.example.baitapthuctap.enums.TaskStatus;

@Getter
@Setter
public class TaskResponse {

    private Integer id;
    private String title;
    private String description;
    private TaskStatus status;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
    }
}
