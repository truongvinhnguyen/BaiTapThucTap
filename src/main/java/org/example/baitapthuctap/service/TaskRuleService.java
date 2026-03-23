package org.example.baitapthuctap.service;

import org.example.baitapthuctap.entity.Task;
import org.example.baitapthuctap.enums.TaskStatus;
import org.springframework.stereotype.Service;

@Service
public class TaskRuleService {

    public void validateUpdateStatus(Task task) {

        if (task.getStatus() == TaskStatus.DONE) {
            throw new RuntimeException("Task đã DONE, không được update");
        }
    }

}