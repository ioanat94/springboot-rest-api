package com.fs12.javaspringboot.admin;

import com.fs12.javaspringboot.util.AdminNotFoundException;
import com.fs12.javaspringboot.util.AdminsNotFoundException;
import com.fs12.javaspringboot.util.EmailAlreadyInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<Admin>> getAdmins() throws AdminsNotFoundException {
        return ResponseEntity.ok(adminService.getAdmins());
    }

    @GetMapping(path = "/sort/{field}")
    public ResponseEntity<List<Admin>> getAdminsWithSorting(@PathVariable String field) throws AdminsNotFoundException {
        return ResponseEntity.ok(adminService.getAdminsWithSorting(field));
    }

    @GetMapping(path = "/page/{offset}/size/{pageSize}")
    public ResponseEntity<Page<Admin>> getAdminsWithPagination(@PathVariable int offset, @PathVariable int pageSize) throws AdminsNotFoundException {
        return ResponseEntity.ok(adminService.getAdminsWithPagination(offset, pageSize));
    }

    @GetMapping(path = "/sort/{field}/page/{offset}/size/{pageSize}")
    public ResponseEntity<Page<Admin>> getAdminsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) throws AdminsNotFoundException {
        return ResponseEntity.ok(adminService.getAdminsWithPaginationAndSorting(offset, pageSize, field));
    }

    @GetMapping(path = "{adminId}")
    public ResponseEntity<Optional<Admin>> getAdmin(@PathVariable("adminId") int adminId) throws AdminNotFoundException {
        return ResponseEntity.ok(adminService.getAdmin(adminId));
    }

    @PostMapping
    public ResponseEntity<Admin> addAdmin(@RequestBody @Valid Admin admin) throws EmailAlreadyInUse {
        return new ResponseEntity<>(adminService.addAdmin(admin), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("adminId") int adminId) throws AdminNotFoundException {
        return ResponseEntity.ok(adminService.deleteAdmin(adminId));
    }

    @PutMapping(path = "{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("adminId") int adminId, @RequestBody Admin admin) throws AdminNotFoundException {
        return new ResponseEntity<>(adminService.updateAdmin(adminId, admin), HttpStatus.OK);
    }
}
