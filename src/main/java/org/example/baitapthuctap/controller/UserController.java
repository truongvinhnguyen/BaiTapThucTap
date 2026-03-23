package org.example.baitapthuctap.controller;

import jakarta.validation.Valid;
import org.example.baitapthuctap.model.request.UserRequest;
import org.example.baitapthuctap.model.response.ResponseObject;
import org.example.baitapthuctap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseObject<?> getAll(){
        return ResponseObject.success(
                userService.getAll(),
                "Lấy danh sách user thành công"
        );
    }

    @PostMapping
    public ResponseObject<?> create(@Valid @RequestBody UserRequest request){
        return ResponseObject.success(
                userService.create(request),
                "Tạo user thành công"
        );
    }

    @DeleteMapping("/{id}")
    public ResponseObject<?> delete(@PathVariable Integer id){

        userService.delete(id);

        return ResponseObject.success(
                null,
                "Xóa user thành công"
        );
    }
}