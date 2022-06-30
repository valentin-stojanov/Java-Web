package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserLoginDto;
import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(CurrentUser currentUser,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       UserMapper userMapper) {
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto) {
        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDto);
        newUser.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));


        newUser = userRepository.save(newUser);
        login(newUser);

    }

    public boolean login(UserLoginDto loginDto) {
        Optional<UserEntity> optUser = userRepository
                .findByEmail(loginDto.getUsername());

        if (optUser.isEmpty()) {
            LOGGER.info("User with name [{}] not found.", loginDto.getUsername());
            return false;
        }

        String rawPassword = loginDto.getPassword();
        String encodedPassword = optUser.get().getPassword();

        boolean success = passwordEncoder.matches(rawPassword, encodedPassword);

        if (success) {
            login(optUser.get());
        } else {
            logout();
        }
        return success;
    }

    public void login(UserEntity userEntity) {
        currentUser
                .setLoggedIn(true)
                .setName(userEntity.getFirstName() + " " + userEntity.getLastName())
                .setEmail(userEntity.getEmail());
    }

    public void logout() {
        currentUser.clear();
    }
}
