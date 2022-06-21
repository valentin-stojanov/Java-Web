package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserLoginDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(UserLoginDto loginDto) {
        Optional<UserEntity> optUser = userRepository.findByEmail(loginDto.getUsername());

        if (optUser.isEmpty()){
            LOGGER.debug("User with name [{}] not found.", loginDto.getUsername());
            return false;
        }

        return optUser.get().getPassword().equals(loginDto.getPassword());
    }
}
