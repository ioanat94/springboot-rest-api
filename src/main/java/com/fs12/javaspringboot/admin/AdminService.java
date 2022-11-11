package com.fs12.javaspringboot.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdmin(int adminId) {
        return adminRepository.findById(adminId);
    }

    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteAdmin(int adminId) {
        boolean exists = adminRepository.existsById(adminId);

        if(!exists) {
            throw new IllegalStateException("Admin with id " + adminId + " does not exist.");
        }

        adminRepository.deleteById(adminId);
    }

    @Transactional
    public void updateAdmin(int adminId, Admin admin) {
        Admin foundAdmin = adminRepository.findById(adminId).orElseThrow(() -> new IllegalStateException("Admin with id " + adminId + " does not exist."));

        if(admin.getFirstName() != null && admin.getFirstName().length() > 0 && !Objects.equals(foundAdmin.getFirstName(), admin.getFirstName())) {
            foundAdmin.setFirstName(admin.getFirstName());
        }

        if(admin.getLastName() != null && admin.getLastName().length() > 0 && !Objects.equals(foundAdmin.getLastName(), admin.getLastName())) {
            foundAdmin.setLastName(admin.getLastName());
        }

        if(admin.getEmail() != null && admin.getEmail().length() > 0 && !Objects.equals(foundAdmin.getEmail(), admin.getEmail())) {
            foundAdmin.setEmail(admin.getEmail());
        }

        if(admin.getPassword() != null && admin.getPassword().length() > 0 && !Objects.equals(foundAdmin.getPassword(), admin.getPassword())) {
            foundAdmin.setPassword(admin.getPassword());
        }

        if(admin.getPermissions() != null && admin.getPermissions().size() > 0 && !Objects.equals(foundAdmin.getPermissions(), admin.getPermissions())) {
            foundAdmin.setPermissions(admin.getPermissions());
        }
    }
}
