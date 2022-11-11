package com.fs12.javaspringboot.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Admin> getAdmins() {
        return adminService.getAdmins();
    }

    @GetMapping(path = "{adminId}")
    public Optional<Admin> getAdmin(@PathVariable("adminId") int adminId) {
        return adminService.getAdmin(adminId);
    }

    @PostMapping
    public void addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
    }

    @DeleteMapping(path = "{adminId}")
    public void deleteAdmin(@PathVariable("adminId") int adminId) {
        adminService.deleteAdmin(adminId);
    }

    @PutMapping(path = "{adminId}")
    public void updateAdmin(@PathVariable("adminId") int adminId, @RequestBody Admin admin) {
        adminService.updateAdmin(adminId, admin);
    }
}
