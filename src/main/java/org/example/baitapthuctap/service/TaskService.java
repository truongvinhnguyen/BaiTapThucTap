package org.example.baitapthuctap.service;

import org.example.baitapthuctap.entity.Project;
import org.example.baitapthuctap.entity.Task;
import org.example.baitapthuctap.entity.User;
import org.example.baitapthuctap.enums.TaskStatus;
import org.example.baitapthuctap.exception.CustomException;
import org.example.baitapthuctap.model.request.TaskRequest;
import org.example.baitapthuctap.model.response.TaskResponse;
import org.example.baitapthuctap.repository.ProjectRepository;
import org.example.baitapthuctap.repository.TaskRepository;
import org.example.baitapthuctap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskResponse::new)
                .toList();
    }

    public TaskResponse add(TaskRequest req) {

        Project project = projectRepository.findById(req.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project không tồn tại"));

        Task task = new Task();
        task.setTitle(req.getTitle());
        task.setDescription(req.getDescription());
        task.setProject(project);

        if(req.getDeadline() != null && req.getDeadline().isBefore(LocalDate.now())){
            throw new CustomException("Deadline phải lớn hơn ngày hiện tại");
        }

        if (req.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        } else {
            task.setStatus(req.getStatus());
        }

        taskRepository.save(task);

        return new TaskResponse(task);
    }

    public TaskResponse update(TaskRequest req, Integer id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại"));

        if (req.getTitle() != null) {
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

        if (!task.getProject().getId().equals(task.getProject().getId())) {
            throw new RuntimeException("User không thuộc project");
        }

        task.setUser(user);

        taskRepository.save(task);

        return new TaskResponse(task);
    }

    public List<TaskResponse> getTaskByUser(Integer userId) {
        return taskRepository.findByUserId(userId)
                .stream()
                .map(TaskResponse::new)
                .toList();
    }

    public List<TaskResponse> getTaskByProject(Integer projectId) {
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(TaskResponse::new)
                .toList();
    }

    public TaskResponse updateStatus(Integer taskId, TaskStatus status) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại"));

        if (task.getStatus() == TaskStatus.DONE) {
            throw new RuntimeException("Task đã DONE không được update");
        }

        task.setStatus(status);

        taskRepository.save(task);

        return new TaskResponse(task);
    }

    public List<Task> getTaskByUsername(String username){
        return taskRepository.findByUserUsername(username);
    }
}