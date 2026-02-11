package org.example.baitapthuctap.model.response;

import lombok.Getter;
import lombok.Setter;
import org.example.baitapthuctap.entity.Task;

@Getter
@Setter
public class TaskResponse {

    private Integer id;
    private String title;
    private String description;
    private Integer status;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
    }
}
