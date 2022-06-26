package bg.softuni.patfinder2.service;

import bg.softuni.patfinder2.model.UserEntity;
import bg.softuni.patfinder2.model.dto.UserRegistrationDTO;
import bg.softuni.patfinder2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                registrationDTO.getPassword(),
                registrationDTO.getEmail(),
                registrationDTO.getFullName(),
                registrationDTO.getAge()
        );

        this.userRepository.save(user);

    }
}
