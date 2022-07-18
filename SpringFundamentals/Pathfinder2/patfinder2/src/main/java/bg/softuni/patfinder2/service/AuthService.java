package bg.softuni.patfinder2.service;

import bg.softuni.patfinder2.model.UserEntity;
import bg.softuni.patfinder2.model.dto.UserRegistrationDTO;
import bg.softuni.patfinder2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("password.match");
        }

        Optional<UserEntity> byEmail = this.userRepository
                .findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()){
            throw new RuntimeException("email.used");
        }

        UserEntity user = new UserEntity(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getEmail(),
                registrationDTO.getFullName(),
                registrationDTO.getAge()
        );

        this.userRepository.save(user);

    }

    public UserEntity getUser(String username){
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
