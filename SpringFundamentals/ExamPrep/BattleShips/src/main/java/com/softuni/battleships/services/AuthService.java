package com.softuni.battleships.services;

import com.softuni.battleships.models.UserEntity;
import com.softuni.battleships.models.dtos.LoginDTO;
import com.softuni.battleships.models.dtos.UserRegistrationDTO;
import com.softuni.battleships.repositories.UserRepository;
import com.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private LoggedUser userSession;

    public AuthService(UserRepository userRepository,
                       LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean register(UserRegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return false;
        }

        Optional<UserEntity> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());
        if (byEmail.isPresent()){
            return false;
        }

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());
        if (byUsername.isPresent()){
            return false;
        }

        UserEntity user = new UserEntity();
        user.setUsername(registrationDTO.getUsername())
                .setEmail(registrationDTO.getEmail())
                .setFullName(registrationDTO.getFullName())
                .setPassword(registrationDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<UserEntity> user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (user.isEmpty()) {
            return false;
        }

        this.userSession.login(user.get());

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() != null;
    }

    public Long getLoggedUserId() {
        return this.userSession.getId();
    }
}
