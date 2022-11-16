package com.fs12.javaspringboot.admin;

import com.fs12.javaspringboot.util.AdminNotFoundException;
import com.fs12.javaspringboot.util.AdminsNotFoundException;
import com.fs12.javaspringboot.util.EmailAlreadyInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Admin> getAdmins() throws AdminsNotFoundException {
        List<Admin> admins = adminRepository.findAll();

        if(!admins.isEmpty()) {
            return admins;
        } else {
            throw new AdminsNotFoundException("No admins found.");
        }
    }

    public Optional<Admin> getAdmin(int adminId) throws AdminNotFoundException {
        Optional<Admin> admin = adminRepository.findById(adminId);

        if(admin.isPresent()) {
            return admin;
        } else {
            throw new AdminNotFoundException("Admin with id " + adminId + " does not exist.");
        }
    }

    public Admin addAdmin(Admin admin) throws EmailAlreadyInUse {
        Admin foundAdmin = adminRepository.getAdminByEmail(admin.getEmail());

        if(foundAdmin != null) {
            throw new EmailAlreadyInUse("An account already exists for this email address.");
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        return adminRepository.save(admin);
    }

    public String deleteAdmin(int adminId) throws AdminNotFoundException {
        boolean exists = adminRepository.existsById(adminId);

        if(!exists) {
            throw new AdminNotFoundException("Admin with id " + adminId + " does not exist.");
        }

        adminRepository.deleteById(adminId);

        return "Admin with id " + adminId + " deleted successfully.";
    }

    @Transactional
    public Admin updateAdmin(int adminId, Admin admin) throws AdminNotFoundException {
        Admin foundAdmin = adminRepository.findById(adminId).orElseThrow(() -> new AdminNotFoundException("Admin with id " + adminId + " does not exist."));

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

        return foundAdmin;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.getAdminByEmail(email);

        if(admin == null) {
            throw new UsernameNotFoundException("Admin with email " + email + " does not exist.");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        admin.getPermissions().forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission.toString()));
        });

        return new User(admin.getEmail(), admin.getPassword(), authorities);
    }
}
