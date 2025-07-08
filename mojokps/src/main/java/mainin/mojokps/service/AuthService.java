package mainin.mojokps.service;

import mainin.mojokps.model.Admin;
import mainin.mojokps.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean authenticate(String username, String password) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            // Sementara: login pakai password polos (disarankan nanti pakai BCrypt)
            return admin.getPassword().equals(password);
        }
        return false;
    }

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username).orElse(null);
    }
}
