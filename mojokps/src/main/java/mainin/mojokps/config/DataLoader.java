package mainin.mojokps.config;

import jakarta.annotation.PostConstruct;
import mainin.mojokps.model.Admin;
import mainin.mojokps.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Autowired
    private AdminRepository adminRepository;

    @PostConstruct
    public void init() {
        if (adminRepository.findAll().isEmpty()) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            adminRepository.save(admin);
        }
    }
}
