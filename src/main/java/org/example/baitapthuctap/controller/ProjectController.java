package org.example.baitapthuctap.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @PostMapping("/create")
    public String createProject() {
        return "Manager tạo project thành công";
    }

}