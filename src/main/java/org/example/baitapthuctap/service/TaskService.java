package org.example.baitapthuctap.service;

import lombok.RequiredArgsConstructor;
import org.example.baitapthuctap.entity.Task;
import org.example.baitapthuctap.entity.User;
import org.example.baitapthuctap.model.request.TaskRequest;
import org.example.baitapthuctap.model.response.TaskResponse;
import org.example.baitapthuctap.repository.TaskRepository;
import org.example.baitapthuctap.repository.UserRepository;
import org.example.baitapthuctap.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskResponse::new)
                .toList();
    }

    public TaskResponse add(TaskRequest req) {

        if (req.getTitle() == null || req.getTitle().isBlank()) {
            throw new RuntimeException("Title không được để trống");
        }

        Task task = new Task();
        task.setTitle(req.getTitle());
        task.setDescription(req.getDescription());
        task.setStatus(req.getStatus() != null ? req.getStatus() : 0);

        taskRepository.save(task);
        return new TaskResponse(task);
    }

    public TaskResponse update(TaskRequest req, Integer id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại"));

        if (req.getTitle() != null && !req.getTitle().isBlank()) {
            task.setTitle(req.getTitle());
        }

        if (req.getDescription() != null) {
            task.setDescription(req.getDescription());
        }

        if (req.getStatus() != null) {
            task.setStatus(req.getStatus());
        }

        taskRepository.save(task);
        return new TaskResponse(task);
    }

    public void delete(Integer id) {

        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task không tồn tại");
        }

        taskRepository.deleteById(id);
    }

    public TaskResponse assignUser(Integer taskId, Integer userId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        task.setUser(user);
        taskRepository.save(task);

        return new TaskResponse(task);
    }

    public List<Task> getTaskByUser(Integer userId){
        return taskRepository.findByUserId(userId);
    }

    public List<Task> getTaskByProject(Integer projectId){
        return taskRepository.findByProjectId(projectId);
    }
}
