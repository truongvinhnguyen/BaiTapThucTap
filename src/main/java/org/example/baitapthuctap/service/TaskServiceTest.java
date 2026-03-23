package org.example.baitapthuctap.service;

import org.example.baitapthuctap.entity.Project;
import org.example.baitapthuctap.entity.Task;
import org.example.baitapthuctap.entity.User;
import org.example.baitapthuctap.model.request.TaskRequest;
import org.example.baitapthuctap.model.response.TaskResponse;
import org.example.baitapthuctap.repository.ProjectRepository;
import org.example.baitapthuctap.repository.TaskRepository;
import org.example.baitapthuctap.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_success() {

        TaskRequest req = new TaskRequest();
        req.setTitle("Task A");
        req.setProjectId(1);

        Project project = new Project();
        project.setId(1);

        Task savedTask = new Task();
        savedTask.setId(1);
        savedTask.setTitle("Task A");
        savedTask.setProject(project);

        when(projectRepository.findById(1))
                .thenReturn(Optional.of(project));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(savedTask);

        TaskResponse result = taskService.add(req);

        assertNotNull(result);
        assertEquals("Task A", result.getTitle());

        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void assignTask_success() {

        Project project = new Project();
        project.setId(1);

        Task task = new Task();
        task.setId(1);
        task.setProject(project);

        User user = new User();
        user.setId(1);

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(taskRepository.save(any())).thenReturn(task);

        TaskResponse result = taskService.assignUser(1,1);

        assertEquals(1, result.getUserId());

        verify(taskRepository).save(task);
    }

    @Test
    void assignTask_taskNotFound() {

        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> taskService.assignUser(1,1));
    }
}