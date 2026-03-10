package org.example.baitapthuctap.controller;

import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.regexp.RE;
import org.example.baitapthuctap.entity.Task;
import org.example.baitapthuctap.model.request.TaskRequest;
import org.example.baitapthuctap.model.response.ResponseObject;
import org.example.baitapthuctap.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseObject<?> getAllTasks() {
        return new ResponseObject<>(taskService.findAll());
    }

    @PostMapping("/addTask")
    public ResponseObject<?> addTask(@RequestBody TaskRequest taskRequest) {
        return new ResponseObject<>(taskService.add(taskRequest), "Thêm task thành công");
    }

    @PutMapping("/updateTask/{id}")
    public ResponseObject<?> updateTask(@PathVariable Integer id, @RequestBody TaskRequest taskRequest) {
        return new ResponseObject<>(taskService.update(taskRequest,id), "Cập nhật thành công");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<?> deleteTask(@PathVariable Integer id) {
        taskService.delete(id);
        return new ResponseObject<>(null, "Xóa thành công");
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseObject<?> assign(@PathVariable Integer taskId, @PathVariable Integer userId) {
        return new ResponseObject<>(taskService.assignUser(taskId, userId), "Gắn task thành công");
    }

    @GetMapping("/user/{userId}")
    public ResponseObject<?> getTaskByUser(@PathVariable Integer userId){
        return ResponseObject.success(taskService.getTaskByUser(userId),"Task theo user");
    }

    @GetMapping("/project/{projectId}")
    public ResponseObject<?> getTaskByProject(@PathVariable Integer projectId){
        return ResponseObject.success(taskService.getTaskByProject(projectId),"Task theo project");
    }
}
