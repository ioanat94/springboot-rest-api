package com.fs12.javaspringboot.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/admins")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmins() {
        return ResponseEntity.ok(adminService.getAdmins());
    }

    @GetMapping(path = "{adminId}")
    public ResponseEntity<Optional<Admin>> getAdmin(@PathVariable("adminId") int adminId) {
        return ResponseEntity.ok(adminService.getAdmin(adminId));
    }

    @PostMapping
    public ResponseEntity<Admin> addAdmin(@RequestBody @Valid Admin admin) {
        return new ResponseEntity<>(adminService.addAdmin(admin), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("adminId") int adminId) {
        return ResponseEntity.ok(adminService.deleteAdmin(adminId));
    }

    @PutMapping(path = "{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("adminId") int adminId, @RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.updateAdmin(adminId, admin), HttpStatus.OK);
    }
}
