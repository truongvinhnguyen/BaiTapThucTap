package org.example.baitapthuctap.controller;

import jakarta.validation.Valid;
import org.example.baitapthuctap.model.request.TaskRequest;
import org.example.baitapthuctap.model.response.ResponseObject;
import org.example.baitapthuctap.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseObject<?> getAllTasks() {
        return ResponseObject.success(
                taskService.findAll(),
                "Lấy danh sách task thành công"
        );
    }

    @PostMapping("/addTask")
    public ResponseObject<?> addTask(@Valid @RequestBody TaskRequest taskRequest) {
        return ResponseObject.success(
                taskService.add(taskRequest),
                "Thêm task thành công"
        );
    }

    @PutMapping("/updateTask/{id}")
    public ResponseObject<?> updateTask(@PathVariable Integer id,
                                        @Valid @RequestBody TaskRequest taskRequest) {

        return ResponseObject.success(
                taskService.update(taskRequest, id),
                "Cập nhật task thành công"
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<?> deleteTask(@PathVariable Integer id) {

        taskService.delete(id);

        return ResponseObject.success(
                null,
                "Xóa task thành công"
        );
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseObject<?> assign(@PathVariable Integer taskId,
                                    @PathVariable Integer userId) {

        return ResponseObject.success(
                taskService.assignUser(taskId, userId),
                "Gán task cho user thành công"
        );
    }

    @GetMapping("/user")
    public ResponseObject<?> getTaskOfUser(Authentication authentication){

        String username = authentication.getName();

        return ResponseObject.success(
                taskService.getTaskByUsername(username),
                "Danh sách task của user"
        );
    }

    @GetMapping("/project/{projectId}")
    public ResponseObject<?> getTaskByProject(@PathVariable Integer projectId){

        return ResponseObject.success(
                taskService.getTaskByProject(projectId),
                "Danh sách task theo project"
        );
    }

    @PutMapping("/updateStatus/{taskId}/status")
    public ResponseObject<?> updateStatus(@PathVariable Integer taskId,
                                          @RequestBody TaskRequest req){

        return ResponseObject.success(
                taskService.updateStatus(taskId, req.getStatus()),
                "Cập nhật trạng thái task thành công"
        );
    }


}